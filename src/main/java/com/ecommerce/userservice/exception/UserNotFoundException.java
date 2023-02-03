package com.ecommerce.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class UserNotFoundException extends RuntimeException {

    private int code;

    private int httpCode;

    public UserNotFoundException() {
        this.code = 2401;
        this.httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public UserNotFoundException(int code, int httpCode, String message) {
        super(message);
        this.code = code;
        this.httpCode = httpCode;
    }

    public UserNotFoundException(String message) {
        super(message);
        this.code = 2401;
        this.httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
