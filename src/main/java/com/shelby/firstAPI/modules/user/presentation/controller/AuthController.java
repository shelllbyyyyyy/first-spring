package com.shelby.firstAPI.modules.user.presentation.controller;

import com.shelby.firstAPI.modules.user.application.usecase.Login;
import com.shelby.firstAPI.modules.user.presentation.dto.request.LoginRequest;
import com.shelby.firstAPI.modules.user.presentation.dto.request.RegisterUserRequest;
import com.shelby.firstAPI.common.responses.ApiResponse;
import com.shelby.firstAPI.common.responses.CommandResponse;
import com.shelby.firstAPI.modules.user.application.usecase.RegisterUser;

import com.shelby.firstAPI.modules.user.presentation.dto.response.JwtResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.shelby.firstAPI.common.Constants.CREATED_USER;
import static com.shelby.firstAPI.common.Constants.SUCCESS;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final RegisterUser registerUser;
    private final Login loginUser;

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponse<Object>> register(@Valid @RequestBody RegisterUserRequest data) {
        final CommandResponse result = registerUser.execute(data);
        final String id =  result.id();
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED.value(),
                        CREATED_USER,
                        response
                ));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<Object>> login(@Valid @RequestBody LoginRequest data, HttpServletResponse response) {
        final JwtResponse user = loginUser.execute(data);
        final String token =  user.getToken();
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);

        Cookie jwtCookie = new Cookie("JWT-TOKEN", user.getToken());
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(60 * 60);
        response.addCookie(jwtCookie);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        SUCCESS,
                        result
                ));
    }

}
