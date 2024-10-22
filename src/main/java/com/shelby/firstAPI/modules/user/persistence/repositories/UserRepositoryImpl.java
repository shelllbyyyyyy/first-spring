package com.shelby.firstAPI.modules.user.persistence.repositories;

import com.shelby.firstAPI.modules.user.domain.entities.UserEntity;
import com.shelby.firstAPI.modules.user.domain.factories.UserMapper;
import com.shelby.firstAPI.modules.user.domain.repositories.UserRepository;
import com.shelby.firstAPI.modules.user.persistence.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaDataUserRepository;

    @Override
    public UserEntity save(UserEntity user) {
        User data = UserMapper.toPersistence(user);
        User result = jpaDataUserRepository.save(data);

        return UserMapper.toDomain(result);
    };

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        Optional<User> result = jpaDataUserRepository.findByEmail(email);

        return result.map(UserMapper::toDomain);
    };

    @Override
    public void delete(UserEntity user) {
        User data = UserMapper.toPersistence(user);
        jpaDataUserRepository.delete(data);
    }
}
