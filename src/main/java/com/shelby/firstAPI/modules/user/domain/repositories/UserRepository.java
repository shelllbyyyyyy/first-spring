package com.shelby.firstAPI.modules.user.domain.repositories;

import com.shelby.firstAPI.modules.user.domain.entities.UserEntity;

import java.util.Optional;

public interface UserRepository {
     UserEntity save(UserEntity user);
     Optional<UserEntity> findByEmail(String email);
     void delete(UserEntity user);
}
