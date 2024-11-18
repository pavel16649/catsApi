package ru.pavel16649.Dto.Owners;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OwnerResponse {
    private Long id;
    private String name;
    private LocalDate birthDate;
}
