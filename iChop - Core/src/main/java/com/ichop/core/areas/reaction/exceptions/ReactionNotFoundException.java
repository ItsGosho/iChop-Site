package com.ichop.core.areas.reaction.exceptions;

public class ReactionNotFoundException extends RuntimeException {

    public ReactionNotFoundException() {
    }

    public ReactionNotFoundException(String message) {
        super(message);
    }
}
