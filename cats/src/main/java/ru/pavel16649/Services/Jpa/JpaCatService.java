package ru.pavel16649.Services.Jpa;

import java.time.LocalDate;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pavel16649.Domain.Cat;
import ru.pavel16649.Dto.Cats.AddCatRequest;
import ru.pavel16649.Dto.Cats.CatResponse;
import ru.pavel16649.Dto.Cats.ListCatsResponse;
import ru.pavel16649.Repositories.JpaCatRepository;
import ru.pavel16649.Services.CatService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JpaCatService implements CatService {
    private final JpaCatRepository catRepository;

    @Override
    public CatResponse addCat(AddCatRequest cat) {
        Cat savedCat = catRepository.save(Cat.builder()
                .name(cat.getName())
                .birthDate(cat.getBirthDate())
                .breed(cat.getBreed())
                .color(cat.getColor())
                .ownerId(cat.getOwnerId()).build());

        return CatResponse.builder()
                .id(savedCat.getId())
                .name(savedCat.getName())
                .birthDate(savedCat.getBirthDate())
                .breed(savedCat.getBreed())
                .color(savedCat.getColor())
                .ownerId(savedCat.getOwnerId())
                .build();
    }

    @Override
    public void deleteCatById(Long catId) {
        catRepository.deleteById(catId);
    }

    @Override
    public void deleteCatsByOwnerId(Long ownerId) {
        catRepository.deleteByOwnerId(ownerId);
    }

    @Override
    public CatResponse findCatById(Long id) {
        Cat cat = catRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found with ID: " + id));

        return CatResponse.builder()
                .id(cat.getId())
                .name(cat.getName())
                .birthDate(cat.getBirthDate())
                .breed(cat.getBreed())
                .color(cat.getColor())
                .ownerId(cat.getOwnerId())
                .build();
    }

    @Override
    public ListCatsResponse getAllCats() {
        return GenerateListCatsResponse(catRepository.findAll());
    }

    @Override
    public ListCatsResponse findCatsByBirthDate(LocalDate birthDate) {
        return GenerateListCatsResponse(catRepository.findCatsByBirthDate(birthDate));
    }

    @Override
    public ListCatsResponse findCatsByColor(String color) {
        return GenerateListCatsResponse(catRepository.findCatsByColor(color));
    }

    @Override
    public ListCatsResponse findCatsByName(String name) {
        return GenerateListCatsResponse(catRepository.findCatsByName(name));
    }

    @Override
    public ListCatsResponse findCatsByBreed(String breed) {
        return GenerateListCatsResponse(catRepository.findCatsByBreed(breed));
    }

    @Override
    public ListCatsResponse findCatsByOwner(Long ownerId) {
        return GenerateListCatsResponse(catRepository.findByOwnerId(ownerId));
    }


    private ListCatsResponse GenerateListCatsResponse(List<Cat> cats) {
        List<CatResponse> catResponses = cats.stream()
                .map(cat -> CatResponse.builder()
                        .id(cat.getId())
                        .name(cat.getName())
                        .birthDate(cat.getBirthDate())
                        .breed(cat.getBreed())
                        .color(cat.getColor())
                        .ownerId(cat.getOwnerId())
                        .build())
                .collect(Collectors.toList());
        return ListCatsResponse.builder()
                .cats(catResponses)
                .size(catResponses.size())
                .build();
    }
}