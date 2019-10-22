package com.ichop.core.areas.token.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "PasswordResetToken")
@Table(name = "password_reset_tokens")
public class PasswordResetToken extends BaseToken {

    private static final Integer TOKEN_EXPIRATION_IN_SECONDS = 60 * 60 * 48;

    public static Integer getTokenExpiration() {
        return TOKEN_EXPIRATION_IN_SECONDS;
    }
}
