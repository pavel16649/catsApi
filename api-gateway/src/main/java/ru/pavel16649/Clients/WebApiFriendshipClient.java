package ru.pavel16649.Clients;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebApiFriendshipClient implements ApiFriendshipClient {
    private static final String BASE_URL = "http://localhost:8081/catsapi/friendships";
    private final WebClient webClient = WebClient.builder().build();

    public void addFriendship(Long catId1, Long catId2) throws Exception {
        webClient.post()
                .uri(BASE_URL + "/{catId1}/{catId2}", catId1, catId2)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void deleteFriendship(Long catId1, Long catId2) throws Exception {
        webClient.delete()
                .uri(BASE_URL + "/{catId1}/{catId2}", catId1, catId2)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public List<Long> getCatFriends(Long catId) {
        return webClient.get()
                .uri(BASE_URL + "/{catId}", catId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Long>>() {})
                .block();
    }

}
