package ru.pavel16649.Services;

import ru.pavel16649.Dto.Cats.AddCatRequest;
import ru.pavel16649.Dto.Cats.CatResponse;
import ru.pavel16649.Dto.Cats.ListCatsResponse;

import java.time.LocalDate;

public interface CatService {
    CatResponse addCat(AddCatRequest cat);
    void deleteCatById(Long catId);
    void deleteCatsByOwnerId(Long ownerId);
    CatResponse findCatById(Long id);
    ListCatsResponse getAllCats();
    ListCatsResponse findCatsByBirthDate(LocalDate birthDate);
    ListCatsResponse findCatsByColor(String color);
    ListCatsResponse findCatsByName(String name);
    ListCatsResponse findCatsByBreed(String breed);
    ListCatsResponse findCatsByOwner(Long ownerId);
}