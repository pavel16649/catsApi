package ru.pavel16649.Clients;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.pavel16649.Dto.Cats.AddCatRequest;
import ru.pavel16649.Dto.Cats.CatResponse;
import ru.pavel16649.Dto.Cats.ListCatsResponse;


@Service
@RequiredArgsConstructor
public class WebApiCatClient implements ApiCatClient {
    private static final String BASE_URL = "http://localhost:8082/catsapi/cats";

    private final WebClient webClient = WebClient.builder().build();


    public ListCatsResponse getAllCats() {
        return webClient.get()
                .uri(BASE_URL)
                .retrieve()
                .bodyToMono(ListCatsResponse.class)
                .block();
    }

    public CatResponse getCatById(Long id) {
        return webClient.get()
                .uri(BASE_URL + "/{id}", id)
                .retrieve()
                .bodyToMono(CatResponse.class)
                .block();
    }

    public CatResponse addCat(AddCatRequest addCatRequest) {
        return webClient.post()
                .uri(BASE_URL)
                .body(Mono.just(addCatRequest), AddCatRequest.class)
                .retrieve()
                .bodyToMono(CatResponse.class)
                .block();
    }

    public void deleteCat(Long id) {
        webClient.delete()
                .uri(BASE_URL + "/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}