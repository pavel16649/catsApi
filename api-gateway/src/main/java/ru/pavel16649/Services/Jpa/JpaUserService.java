package ru.pavel16649.Services.Jpa;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pavel16649.Domain.User;
import ru.pavel16649.Dto.Users.AddUserRequest;
import ru.pavel16649.Repositories.JpaUserRepository;
import ru.pavel16649.Services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JpaUserService implements UserService {
    private final JpaUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User addUser(AddUserRequest user) {
        Optional<User> tryFindUser = userRepository.findByUsername(user.getUsername());
        if (tryFindUser.isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        return userRepository.save(User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(user.getRoles())
                .id(user.getId()).build());
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " not found"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    @Override
    public void setPassword(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User addRole(String username, String roleToAdd) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        String[] roleArray = user.getRoles().split(",");
        List<String> newRoleList = new ArrayList<>();
        for (String role : roleArray) {
            if (!role.trim().equals(roleToAdd)) {
                newRoleList.add(role.trim());
            }
        }
        newRoleList.add(roleToAdd);
        user.setRoles(String.join(",", newRoleList));
        userRepository.save(user);
        return user;
    }

    @Override
    public User deleteRole(String username, String roleToRemove) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        String[] roleArray = user.getRoles().split(",");
        List<String> newRoleList = new ArrayList<>();
        for (String role : roleArray) {
            if (!role.trim().equals(roleToRemove)) {
                newRoleList.add(role.trim());
            }
        }
        user.setRoles(String.join(",", newRoleList));
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean usernameTaken(String username) {
        Optional<User> tryFindUser = userRepository.findByUsername(username);
        return tryFindUser.isPresent();
    }

    @Override
    public void clear() {
        userRepository.deleteAll();
    }
}
