package com.shelby.firstAPI.common.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import static com.shelby.firstAPI.common.Utils.getStatusFromCode;

@Data
public class ErrorResponse {
    private final int code;
    private String status;
    private final String error;
    private String stackTrace;

    public ErrorResponse(int code, String error) {
        this.status = getStatusFromCode(code);
        this.code = code;
        this.error = error;
    }
}
