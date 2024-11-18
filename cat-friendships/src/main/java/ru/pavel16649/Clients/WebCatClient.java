package ru.pavel16649.Clients;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.pavel16649.Dto.Cats.CatResponse;
import ru.pavel16649.Dto.Owners.OwnerResponse;

import java.util.Optional;

@Component
public class WebCatClient implements CatClient {
    private static final String BASE_URL = "http://localhost:8082/catsapi/cats";

    private final WebClient webClient;

    public WebCatClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Optional<CatResponse> findById(Long id) {
        String url = BASE_URL + "/{id}";
        return webClient.get()
                .uri(url, id)
                .retrieve()
                .bodyToMono(CatResponse.class)
                .blockOptional();
    }
}