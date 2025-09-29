package com.fabrick.bank.exception;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private List<Error> errors;

    @Data
    public static class Error {
        private String code;
        private String description;
    }
}