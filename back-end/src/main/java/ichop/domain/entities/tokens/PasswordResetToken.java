package ichop.domain.entities.tokens;

import ichop.domain.entities.base.Token;

import javax.persistence.*;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken extends Token {

    private static final Integer TOKEN_EXPIRATION_IN_SECONDS = 60 * 60 * 48;

    public static Integer getTokenExpiration() {
        return TOKEN_EXPIRATION_IN_SECONDS;
    }
}
