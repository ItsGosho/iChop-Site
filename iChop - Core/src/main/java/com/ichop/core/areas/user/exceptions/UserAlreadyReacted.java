package com.ichop.core.areas.user.exceptions;

public class UserAlreadyReacted extends RuntimeException {

    public UserAlreadyReacted() {
    }

    public UserAlreadyReacted(String message) {
        super(message);
    }
}
