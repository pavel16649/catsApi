package ru.pavel16649.Dto.Cats;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CatResponse {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String breed;
    private String color;
    private Long ownerId;
}