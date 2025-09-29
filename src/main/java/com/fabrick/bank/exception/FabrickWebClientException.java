package com.fabrick.bank.exception;

public class FabrickWebClientException extends RuntimeException{

    private final ErrorResponse error;

    public FabrickWebClientException(ErrorResponse error) {
        this.error = error;
    }

    public ErrorResponse getError() {
        return error;
    }
}
