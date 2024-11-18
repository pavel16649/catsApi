package ru.pavel16649.Listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.pavel16649.RabbitMessages.FriendshipMessage;
import ru.pavel16649.Services.FriendshipService;

@EnableRabbit
@Component
@RequiredArgsConstructor
public class RabbitMqFriendshipListener {
    private final FriendshipService friendshipService;

    @RabbitListener(queues = "friendshipDeleteQueue")
    private void DeleteFriendship(String messageJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        FriendshipMessage longMessage = objectMapper.readValue(messageJson, FriendshipMessage.class);

        Long catId1 = longMessage.getCatId1();
        Long catId2 = longMessage.getCatId2();

        friendshipService.removeFriendship(catId1, catId2);
    }

    @RabbitListener(queues = "friendshipAddQueue")
    private void AddFriendship(String messageJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        FriendshipMessage longMessage = objectMapper.readValue(messageJson, FriendshipMessage.class);

        Long catId1 = longMessage.getCatId1();
        Long catId2 = longMessage.getCatId2();

        friendshipService.addFriendship(catId1, catId2);
    }

    @RabbitListener(queues = "allCatFriendshipsDeleteQueue")
    private void DeleteAllCatFriendships(String message) {
        Long catId = Long.parseLong(message);

        friendshipService.deleteFriendshipsByCatId(catId);
    }
}
