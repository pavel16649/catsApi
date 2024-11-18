package ru.pavel16649.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.pavel16649.Clients.ApiCatClient;
import ru.pavel16649.Clients.ApiFriendshipClient;
import ru.pavel16649.Dto.Cats.CatResponse;
import ru.pavel16649.Services.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("catsapi/friendships")
public class ApiFriendshipController {
    private final ApiFriendshipClient apiFriendshipClient;
    private final ApiCatClient apiCatClient;
    private final UserService userService;

    @PostMapping("/{catId1}/{catId2}")
    public void addFriendship(@PathVariable Long catId1,
                              @PathVariable Long catId2,
                              Authentication authentication) throws Exception {
        CatResponse cat = apiCatClient.getCatById(catId1);
        String username = userService.findById(cat.getOwnerId()).getUsername();
        if (!username.equals(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        apiFriendshipClient.addFriendship(catId1, catId2);
    }

    @DeleteMapping("/{catId1}/{catId2}")
    public void deleteFriendship(@PathVariable Long catId1,
                                 @PathVariable Long catId2,
                                 Authentication authentication) throws Exception {
        CatResponse cat = apiCatClient.getCatById(catId1);
        String username = userService.findById(cat.getOwnerId()).getUsername();
        if (!username.equals(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        apiFriendshipClient.deleteFriendship(catId1, catId2);
    }

    @GetMapping("/{catId}")
    public List<Long> getCatFriends(@PathVariable Long catId,
                                    Authentication authentication) {
        CatResponse cat = apiCatClient.getCatById(catId);
        String username = userService.findById(cat.getOwnerId()).getUsername();
        if (!username.equals(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return apiFriendshipClient.getCatFriends(catId);
    }
}

