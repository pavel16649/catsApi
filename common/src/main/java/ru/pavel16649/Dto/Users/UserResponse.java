package ru.pavel16649.Dto.Users;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String username;
}
