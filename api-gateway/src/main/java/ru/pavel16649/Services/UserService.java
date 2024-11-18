package ru.pavel16649.Services;

import ru.pavel16649.Domain.User;
import ru.pavel16649.Dto.Users.AddUserRequest;

public interface UserService {
    User addUser(AddUserRequest user);
    User findById(Long id);
    User findByUsername(String username);
    void setPassword(String username, String password);
    User addRole(String username, String role);
    User deleteRole(String username, String role);
    boolean usernameTaken(String username);
    void clear();
}
