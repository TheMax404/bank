package com.fabrick.bank.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(FabrickWebClientException.class)
    public ResponseEntity<ErrorResponse> handleFabrick(FabrickWebClientException ex) {
        return ResponseEntity.badRequest().body(ex.getError());
    }
}