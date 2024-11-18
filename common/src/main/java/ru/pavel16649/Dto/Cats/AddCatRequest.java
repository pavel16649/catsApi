package ru.pavel16649.Dto.Cats;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AddCatRequest {
    private String name;
    private LocalDate birthDate;
    private String breed;
    private String color;
    private Long ownerId;
}