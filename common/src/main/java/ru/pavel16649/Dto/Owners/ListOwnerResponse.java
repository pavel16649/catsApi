package ru.pavel16649.Dto.Owners;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListOwnerResponse {
    private List<OwnerResponse> owners;
    private Integer size;
}
