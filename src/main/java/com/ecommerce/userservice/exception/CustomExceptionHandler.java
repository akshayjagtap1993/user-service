package com.ecommerce.userservice.exception;

import com.ecommerce.userservice.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BaseResponse> handleUserNotFoundException(UserNotFoundException ex) {
        System.out.println("Inside RestControllerAdvice handleUserNotFoundException");
        return ResponseEntity.status(ex.getHttpCode()).body(new BaseResponse(ex.getCode(), ex.getMessage(), "ERROR"));
    }
}
