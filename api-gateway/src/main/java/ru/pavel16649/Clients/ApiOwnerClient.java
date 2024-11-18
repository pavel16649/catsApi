package ru.pavel16649.Clients;

import org.springframework.stereotype.Service;
import ru.pavel16649.Dto.Owners.AddOwnerRequest;
import ru.pavel16649.Dto.Owners.ListOwnerResponse;
import ru.pavel16649.Dto.Owners.OwnerResponse;

public interface ApiOwnerClient {
    public ListOwnerResponse getAllOwners();

    public OwnerResponse getOwnerById(Long id);

    public OwnerResponse addOwner(AddOwnerRequest addOwnerRequest) throws Exception;

    public void deleteOwner(Long id);
}
