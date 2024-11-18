package ru.pavel16649.Clients;

import ru.pavel16649.Dto.Cats.CatResponse;

import java.util.Optional;

public interface CatClient {
    Optional<CatResponse> findById(Long id);
}
