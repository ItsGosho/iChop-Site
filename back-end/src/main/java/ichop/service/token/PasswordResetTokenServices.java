package ichop.service.token;

import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.entities.users.User;

public interface PasswordResetTokenServices {
    boolean isValid(String token);

    PasswordResetToken createToken(User user);

    PasswordResetToken getTokenByUser(User user);

    PasswordResetToken getTokenByToken(String token);

    void deleteOldestToken(User user);
}
