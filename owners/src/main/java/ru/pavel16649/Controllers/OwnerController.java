package ru.pavel16649.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import ru.pavel16649.Dto.Owners.AddOwnerRequest;
import ru.pavel16649.Dto.Owners.ListOwnerResponse;
import ru.pavel16649.Dto.Owners.OwnerResponse;
import ru.pavel16649.Services.OwnerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("catsapi/owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final RabbitTemplate rabbitTemplate;

    @GetMapping
    public ListOwnerResponse getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    public OwnerResponse getOwnerById(@PathVariable Long id) {
        return ownerService.findById(id);
    }

    @PostMapping
    public OwnerResponse addOwner(@RequestBody AddOwnerRequest addOwnerRequest) throws Exception {
        return ownerService.addOwner(addOwnerRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        rabbitTemplate.convertAndSend("catOwnerDeleteQueue", id.toString());
    }
}
