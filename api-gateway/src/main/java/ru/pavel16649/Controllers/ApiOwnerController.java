package ru.pavel16649.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.pavel16649.Clients.ApiOwnerClient;
import ru.pavel16649.Domain.User;
import ru.pavel16649.Dto.Owners.AddOwnerRequest;
import ru.pavel16649.Dto.Owners.ListOwnerResponse;
import ru.pavel16649.Dto.Owners.OwnerResponse;
import ru.pavel16649.Dto.Users.AddUserRequest;
import ru.pavel16649.Dto.Users.ListUserResponse;
import ru.pavel16649.Dto.Users.UserResponse;
import ru.pavel16649.Services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("catsapi/owners")
public class ApiOwnerController {
    private final ApiOwnerClient apiOwnerClient;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ListUserResponse getAllOwners() {
        ListOwnerResponse response = apiOwnerClient.getAllOwners();
        List<UserResponse> userResponseList = new ArrayList<>();
        for (OwnerResponse ownerResponse : response.getOwners()) {
            UserResponse resp = new UserResponse();
            resp.setId(ownerResponse.getId());
            resp.setName(ownerResponse.getName());
            resp.setBirthDate(ownerResponse.getBirthDate());
            resp.setUsername(
                    userService.findById(ownerResponse.getId()).getUsername());
            userResponseList.add(resp);
        }
        return ListUserResponse.builder().users(userResponseList).size(userResponseList.size()).build();
    }

    @GetMapping("/{id}")
    public UserResponse getOwnerById(@PathVariable Long id, Authentication authentication) {
        String username = userService.findById(id).getUsername();
        if (!username.equals(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        OwnerResponse ownerResponse = apiOwnerClient.getOwnerById(id);
        UserResponse resp = new UserResponse();
        resp.setId(ownerResponse.getId());
        resp.setName(ownerResponse.getName());
        resp.setBirthDate(ownerResponse.getBirthDate());
        resp.setUsername(username);
        return resp;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserResponse addOwner(@Valid @RequestBody AddUserRequest addUserRequest) throws Exception {
        if (userService.usernameTaken(addUserRequest.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        OwnerResponse ownerResponse = apiOwnerClient.addOwner(AddOwnerRequest.builder()
                .name(addUserRequest.getName())
                .birthDate(addUserRequest.getBirthdate()).build());
        User user = userService.addUser(AddUserRequest.builder()
                .id(ownerResponse.getId())
                .username(addUserRequest.getUsername())
                .password(addUserRequest.getPassword())
                .roles(addUserRequest.getRoles()).build());
        UserResponse resp = new UserResponse();
        resp.setId(ownerResponse.getId());
        resp.setName(ownerResponse.getName());
        resp.setBirthDate(ownerResponse.getBirthDate());
        resp.setUsername(user.getUsername());

        return resp;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteOwner(@PathVariable Long id) {
        apiOwnerClient.deleteOwner(id);
    }
}
