package ru.pavel16649.Clients;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.pavel16649.Dto.Owners.OwnerResponse;

import java.util.Optional;

@Component
public class WebOwnerClient implements OwnerClient {
    private static final String BASE_URL = "http://localhost:8083/catsapi/owners";

    private final WebClient webClient;

    public WebOwnerClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Optional<OwnerResponse> findById(Long id) {
        String url = BASE_URL + "/{id}";
        return webClient.get()
                .uri(url, id)
                .retrieve()
                .bodyToMono(OwnerResponse.class)
                .blockOptional();
    }
}
