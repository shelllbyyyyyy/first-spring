package com.shelby.firstAPI.modules.user.application.usecase;

import com.shelby.firstAPI.common.exceptions.ElementAlreadyExistsException;
import com.shelby.firstAPI.modules.user.presentation.dto.request.RegisterUserRequest;
import com.shelby.firstAPI.common.responses.CommandResponse;
import com.shelby.firstAPI.modules.user.domain.entities.UserEntity;
import com.shelby.firstAPI.modules.user.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.shelby.firstAPI.common.Constants.ALREADY_EXISTS_USER_EMAIL;

@Service
@Slf4j(topic = "Register User")
@RequiredArgsConstructor
public class RegisterUser {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public CommandResponse execute(RegisterUserRequest data) {
        if (userService.findByEmail(data.getEmail()).isPresent()) {
            log.info(ALREADY_EXISTS_USER_EMAIL);
            throw new ElementAlreadyExistsException(ALREADY_EXISTS_USER_EMAIL);
        }

        String hashedPassword = passwordEncoder.encode(data.getPassword());

        final UserEntity user = userService.createUser(data.getUsername(), data.getEmail(), hashedPassword);

        return CommandResponse.builder().id(user.getId()).build();
    };
}
