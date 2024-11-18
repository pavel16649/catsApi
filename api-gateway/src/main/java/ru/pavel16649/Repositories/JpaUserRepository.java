package ru.pavel16649.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel16649.Domain.User;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
