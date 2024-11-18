package ru.pavel16649.Dto.Users;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AddUserRequest {
    @NotNull
    @Min(0)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String roles;
    @NotNull
    private String name;
    @NotNull
    private LocalDate birthdate;
}
