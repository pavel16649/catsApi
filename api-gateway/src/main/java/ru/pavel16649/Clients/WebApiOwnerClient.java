package ru.pavel16649.Clients;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.pavel16649.Dto.Owners.AddOwnerRequest;
import ru.pavel16649.Dto.Owners.ListOwnerResponse;
import ru.pavel16649.Dto.Owners.OwnerResponse;

@Service
public class WebApiOwnerClient implements ApiOwnerClient {
    private static final String BASE_URL = "http://localhost:8083/catsapi/owners";

    private final WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();

    public ListOwnerResponse getAllOwners() {
        return webClient.get()
                .uri(BASE_URL)
                .retrieve()
                .bodyToMono(ListOwnerResponse.class)
                .block();
    }

    public OwnerResponse getOwnerById(Long id) {
        return webClient.get()
                .uri(BASE_URL + "/{id}", id)
                .retrieve()
                .bodyToMono(OwnerResponse.class)
                .block();
    }

    public OwnerResponse addOwner(AddOwnerRequest addOwnerRequest) throws Exception {
        return webClient.post()
                .uri(BASE_URL)
                .body(Mono.just(addOwnerRequest), AddOwnerRequest.class)
                .retrieve()
                .bodyToMono(OwnerResponse.class)
                .block();
    }

    public void deleteOwner(Long id) {
        webClient.delete()
                .uri(BASE_URL + "/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
