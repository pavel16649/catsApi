package ru.pavel16649.Clients;

import ru.pavel16649.Dto.Owners.OwnerResponse;

import java.util.Optional;

public interface OwnerClient {
    Optional<OwnerResponse> findById(Long id);
}
