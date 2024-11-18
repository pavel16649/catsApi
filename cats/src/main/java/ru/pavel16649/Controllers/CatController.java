package ru.pavel16649.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.pavel16649.Clients.OwnerClient;
import ru.pavel16649.Dto.Cats.AddCatRequest;
import ru.pavel16649.Dto.Cats.CatResponse;
import ru.pavel16649.Dto.Cats.ListCatsResponse;
import ru.pavel16649.Dto.Owners.OwnerResponse;
import ru.pavel16649.RabbitMessages.FriendshipMessage;
import ru.pavel16649.Services.CatService;

@RestController
@RequiredArgsConstructor
@RequestMapping("catsapi/cats")
public class CatController {
    private final CatService catService;
    private final OwnerClient ownerClient;
    private final RabbitTemplate rabbitTemplate;

    @GetMapping
    public ListCatsResponse getAllCats() {
        return catService.getAllCats();
    }

    @GetMapping("/{id}")
    public CatResponse getCatById(@PathVariable Long id) {
        return catService.findCatById(id);
    }

    @PostMapping
    public CatResponse addCat(@RequestBody AddCatRequest addCatRequest) throws Exception {
        Long ownerId = addCatRequest.getOwnerId();
        OwnerResponse owner = ownerClient.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with ID: " + ownerId.toString()));
        return catService.addCat(addCatRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable Long id) {
        rabbitTemplate.convertAndSend("catDeleteQueue", id.toString());
        rabbitTemplate.convertAndSend("allCatFriendshipsDeleteQueue", id.toString());
    }
}