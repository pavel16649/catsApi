package ru.pavel16649.Clients;

import ru.pavel16649.Dto.Cats.AddCatRequest;
import ru.pavel16649.Dto.Cats.CatResponse;
import ru.pavel16649.Dto.Cats.ListCatsResponse;

public interface ApiCatClient {
    public ListCatsResponse getAllCats();
    public CatResponse getCatById(Long id);
    public CatResponse addCat(AddCatRequest addCatRequest);
    public void deleteCat(Long id);

}
