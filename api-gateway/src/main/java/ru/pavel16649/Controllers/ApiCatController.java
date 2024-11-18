package ru.pavel16649.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.pavel16649.Clients.ApiCatClient;
import ru.pavel16649.Dto.Cats.AddCatRequest;
import ru.pavel16649.Dto.Cats.CatResponse;
import ru.pavel16649.Dto.Cats.ListCatsResponse;
import ru.pavel16649.Services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("catsapi/cats")
public class ApiCatController {
    private final ApiCatClient apiCatClient;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ListCatsResponse getAllCats() {
        return apiCatClient.getAllCats();
    }

    @GetMapping("/{id}")
    public CatResponse getCatById(@PathVariable Long id,
                                  Authentication authentication) {
        CatResponse cat = apiCatClient.getCatById(id);
        String username = userService.findById(cat.getOwnerId()).getUsername();
        if (!username.equals(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return cat;
    }

    @PostMapping
    public CatResponse addCat(@Valid @RequestBody AddCatRequest addCatRequest,
                              Authentication authentication) {
        String username = userService.findById(addCatRequest.getOwnerId()).getUsername();
        if (!username.equals(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return apiCatClient.addCat(addCatRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable Long id,
                          Authentication authentication) {
        CatResponse cat = apiCatClient.getCatById(id);
        String username = userService.findById(cat.getOwnerId()).getUsername();
        if (!username.equals(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        apiCatClient.deleteCat(id);
    }
}