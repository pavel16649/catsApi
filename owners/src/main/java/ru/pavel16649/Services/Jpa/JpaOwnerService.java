package ru.pavel16649.Services.Jpa;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pavel16649.Domain.Owner;
import ru.pavel16649.Dto.Owners.AddOwnerRequest;
import ru.pavel16649.Dto.Owners.ListOwnerResponse;
import ru.pavel16649.Dto.Owners.OwnerResponse;
import ru.pavel16649.Repositories.JpaOwnerRepository;
import ru.pavel16649.Services.OwnerService;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JpaOwnerService implements OwnerService {
    private final JpaOwnerRepository ownerRepository;

    @Override
    public OwnerResponse addOwner(AddOwnerRequest owner) {
        Owner ownerToSave = ownerRepository.save(Owner.builder()
                .birthDate(owner.getBirthDate())
                .name(owner.getName()).build());

        return OwnerResponse.builder()
                .id(ownerToSave.getId())
                .birthDate(ownerToSave.getBirthDate())
                .name(ownerToSave.getName()).build();
    }


    @Override
    @Transactional
    public void deleteOwner(Long ownerId) {
        ownerRepository.deleteById(ownerId);
    }

    @Override
    public ListOwnerResponse getAllOwners() {
        return GenerateListOwnerResponse(ownerRepository.findAll());
    }

    @Override
    public OwnerResponse findById(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with ID: " + id));

        return OwnerResponse.builder()
                .id(owner.getId())
                .name(owner.getName())
                .birthDate(owner.getBirthDate())
                .build();
    }

    @Override
    public ListOwnerResponse findByBirthDate(LocalDate birthDate) {
        return GenerateListOwnerResponse(ownerRepository.findByBirthDate(birthDate));
    }

    @Override
    public ListOwnerResponse findByName(String name) {
        return GenerateListOwnerResponse(ownerRepository.findByName(name));
    }

    private ListOwnerResponse GenerateListOwnerResponse(List<Owner> owners) {
        List<OwnerResponse> ownerResponses = owners.stream()
                .map(owner -> OwnerResponse.builder()
                        .id(owner.getId())
                        .name(owner.getName())
                        .birthDate(owner.getBirthDate()).build())
                .collect(Collectors.toList());
        return ListOwnerResponse.builder()
                .owners(ownerResponses)
                .size(ownerResponses.size())
                .build();
    }
}