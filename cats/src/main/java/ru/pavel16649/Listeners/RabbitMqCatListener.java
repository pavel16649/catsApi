package ru.pavel16649.Listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.pavel16649.Dto.Cats.AddCatRequest;
import ru.pavel16649.Services.CatService;

@EnableRabbit
@Component
@RequiredArgsConstructor
public class RabbitMqCatListener {
    private final CatService catService;

    @RabbitListener(queues = "catOwnerDeleteQueue")
    private void DeleteCatsByOwnerId(String ownerId) {
        Long longOwnerId = Long.parseLong(ownerId, 10);
        catService.deleteCatsByOwnerId(longOwnerId);
    }

    @RabbitListener(queues = "catDeleteQueue")
    private void DeleteCat(String catId) {
        Long longCatId = Long.parseLong(catId, 10);
        catService.deleteCatById(longCatId);
    }

    @RabbitListener(queues = "catAddQueue")
    private void AddCat(String messageJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AddCatRequest addCatRequest = objectMapper.readValue(messageJson, AddCatRequest.class);
        catService.addCat(addCatRequest);
    }
}
