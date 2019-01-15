package itsgosho.service.token;

import itsgosho.domain.entities.tokens.PasswordResetToken;
import itsgosho.domain.entities.tokens.Token;
import itsgosho.domain.entities.users.User;

public interface PasswordResetTokenServices {
    boolean isValid(String token);

    PasswordResetToken createToken(User user);

    PasswordResetToken getTokenByUser(User user);

    PasswordResetToken getTokenByToken(String token);

    boolean deleteToken(User user);
}
