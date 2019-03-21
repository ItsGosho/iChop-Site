package com.ichop.core.exceptions.role;

public class UserRoleNotFoundException extends RuntimeException {

    public UserRoleNotFoundException() {
    }

    public UserRoleNotFoundException(String message) {
        super(message);
    }
}
