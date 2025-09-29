package com.fabrick.bank.exception;


import lombok.Getter;

public class BankException extends RuntimeException{

    @Getter
    private final CodeError codeError;
    @Getter
    private final String message;

    public BankException(CodeError codeError,String message) {
        this.codeError = codeError;
        this.message = message;
    }

}
