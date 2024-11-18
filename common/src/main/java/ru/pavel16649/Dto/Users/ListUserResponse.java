package ru.pavel16649.Dto.Users;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListUserResponse {
    private List<UserResponse> users;
    private Integer size;
}
