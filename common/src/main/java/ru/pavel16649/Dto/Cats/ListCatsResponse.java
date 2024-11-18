package ru.pavel16649.Dto.Cats;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListCatsResponse {
    private List<CatResponse> cats;
    private Integer size;
}
