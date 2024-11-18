package ru.pavel16649.Services;


import ru.pavel16649.Dto.Owners.AddOwnerRequest;
import ru.pavel16649.Dto.Owners.ListOwnerResponse;
import ru.pavel16649.Dto.Owners.OwnerResponse;

import java.time.LocalDate;

public interface OwnerService {
    OwnerResponse addOwner(AddOwnerRequest owner);
    void deleteOwner(Long ownerId);
    ListOwnerResponse getAllOwners();
    OwnerResponse findById(Long id);
    ListOwnerResponse findByBirthDate(LocalDate birthDate);
    ListOwnerResponse findByName(String name);
}
