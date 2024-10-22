package com.shelby.firstAPI.modules.user.application.usecase;

import com.shelby.firstAPI.common.exceptions.NoSuchElementFoundException;
import com.shelby.firstAPI.modules.user.domain.entities.UserEntity;
import com.shelby.firstAPI.modules.user.domain.services.UserService;
import com.shelby.firstAPI.modules.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.shelby.firstAPI.common.Constants.NOT_FOUND;

@Service
@Slf4j(topic = "Find By Email")
@RequiredArgsConstructor
public class FindByEmail {
    private final UserService userService;

    public Optional<UserResponse> execute(String email) {
        final Optional<UserEntity> result = userService.findByEmail(email);

        if (result.isEmpty()) {
            log.info(NOT_FOUND);
            throw new NoSuchElementFoundException(NOT_FOUND);
        }


        return result.map(UserResponse::toResponse);
    }
}
