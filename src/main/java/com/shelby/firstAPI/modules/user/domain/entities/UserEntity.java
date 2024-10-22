package com.shelby.firstAPI.modules.user.domain.entities;

import lombok.*;

@Data
public class UserEntity {
    private final String id;
    private String username;
    private String email;
    private String password;
}
