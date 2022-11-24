package org.psu.lab5.controller;

import org.psu.lab5.exception.LoginException;
import org.psu.lab5.pojo.LoginErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthErrorHandler {
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<LoginErrorResponse> loginExceptionHandler(LoginException exc) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new LoginErrorResponse(exc.getMessage()));
    }
}
