package ru.pavel16649.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.pavel16649.Clients.CatClient;
import ru.pavel16649.Dto.Cats.CatResponse;
import ru.pavel16649.RabbitMessages.FriendshipMessage;
import ru.pavel16649.Services.FriendshipService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("catsapi/friendships")
public class FriendshipController {
    private final FriendshipService friendshipService;
    private final CatClient catClient;
    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/{catId1}/{catId2}")
    public void addFriendship(@PathVariable Long catId1,
                              @PathVariable Long catId2) throws Exception {
        CatResponse cat1 = catClient.findById(catId1)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found with ID: " + catId1.toString()));
        CatResponse cat2 = catClient.findById(catId2)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found with ID: " + catId2.toString()));

        ObjectMapper objectMapper = new ObjectMapper();
        FriendshipMessage message = new FriendshipMessage();
        message.setCatId1(catId1);
        message.setCatId2(catId2);
        byte[] messageBytes = objectMapper.writeValueAsBytes(message);

        rabbitTemplate.convertAndSend("friendshipAddQueue", messageBytes);
    }

    @DeleteMapping("/{catId1}/{catId2}")
    public void deleteFriendship(@PathVariable Long catId1,
                                 @PathVariable Long catId2) throws Exception {
        CatResponse cat1 = catClient.findById(catId1)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found with ID: " + catId1.toString()));
        CatResponse cat2 = catClient.findById(catId2)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found with ID: " + catId2.toString()));

        ObjectMapper objectMapper = new ObjectMapper();
        FriendshipMessage message = new FriendshipMessage();
        message.setCatId1(catId1);
        message.setCatId2(catId2);
        String messageJson = objectMapper.writeValueAsString(message);

        rabbitTemplate.convertAndSend("friendshipDeleteQueue", messageJson);
    }

    @GetMapping("/{catId}")
    public List<Long> getCatFriends(@PathVariable Long catId) {
        CatResponse cat = catClient.findById(catId)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found with ID: " + catId.toString()));

        return friendshipService.findFriendsOfCat(catId);
    }
}