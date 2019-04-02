package com.ichop.core.areas.user.exceptions;

public class UserPasswordNotValidException extends RuntimeException {

    public UserPasswordNotValidException() {
    }

    public UserPasswordNotValidException(String message) {
        super(message);
    }
}
