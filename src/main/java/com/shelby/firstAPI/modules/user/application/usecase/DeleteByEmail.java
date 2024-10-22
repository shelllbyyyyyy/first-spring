package com.shelby.firstAPI.modules.user.application.usecase;

import com.shelby.firstAPI.modules.user.domain.entities.UserEntity;
import com.shelby.firstAPI.modules.user.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

import static com.shelby.firstAPI.common.Constants.SUCCESS;


@Service
@Slf4j(topic = "Delete By Email")
@RequiredArgsConstructor
public class DeleteByEmail {
    private final UserService userService;

    public boolean execute(String email) {
        final Optional<UserEntity> user = userService.findByEmail(email);
        if(user.isPresent()) {
            userService.delete(user.get());
            log.info(SUCCESS + "Delete account");
            return true;
        }

        return false;
    }
}

