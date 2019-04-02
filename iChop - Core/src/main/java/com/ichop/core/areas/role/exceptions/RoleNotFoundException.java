package com.ichop.core.areas.role.exceptions;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
