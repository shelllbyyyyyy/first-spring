package com.shelby.firstAPI.modules.user.domain.factories;

import com.shelby.firstAPI.modules.user.domain.entities.UserEntity;
import com.shelby.firstAPI.modules.user.persistence.models.User;
import com.shelby.firstAPI.modules.user.presentation.dto.response.UserResponse;

public class UserMapper {
    public static UserEntity toDomain(User user) {
        UserEntity data = new UserEntity(user.getId().toString());
        data.setUsername(user.getUsername());
        data.setEmail(user.getEmail());
        data.setPassword(user.getPassword());

        return data;
    }

    public static UserEntity toDomain(UserResponse user) {
        UserEntity data = new UserEntity(user.getId());
        data.setUsername(user.getUsername());
        data.setEmail(user.getEmail());
        data.setPassword(user.getPassword());

        return data;
    }

    public static User toPersistence(UserEntity userEntity) {
        User user = new User();
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());

        return user;
    }
}
