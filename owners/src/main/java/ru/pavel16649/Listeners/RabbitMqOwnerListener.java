package ru.pavel16649.Listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.pavel16649.Dto.Owners.AddOwnerRequest;
import ru.pavel16649.Services.OwnerService;

@EnableRabbit
@Component
@RequiredArgsConstructor
public class RabbitMqOwnerListener {
    private final OwnerService ownerService;

    @RabbitListener(queues = "ownerDeleteQueue")
    private void DeleteOwner(String ownerId) {
        Long longOwnerId = Long.parseLong(ownerId, 10);
        ownerService.deleteOwner(longOwnerId);
    }

    @RabbitListener(queues = "ownerAddQueue")
    private void AddOwner(String messageJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AddOwnerRequest addOwnerRequest = objectMapper.readValue(messageJson, AddOwnerRequest.class);
        ownerService.addOwner(addOwnerRequest);
    }
}
