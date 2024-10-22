package com.shelby.firstAPI.modules.user.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shelby.firstAPI.modules.user.domain.entities.UserEntity;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UserResponse {
    private final String id;
    private final String username;
    private final String email;
    @JsonIgnore
    private final String password;

    public static UserResponse toResponse (UserEntity user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }
}


