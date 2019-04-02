package com.ichop.core.areas.token.exceptions;

public class TokenNotValidException extends RuntimeException {

    public TokenNotValidException() {
    }

    public TokenNotValidException(String message) {
        super(message);
    }
}
