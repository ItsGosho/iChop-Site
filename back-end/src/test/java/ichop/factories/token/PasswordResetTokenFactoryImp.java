package ichop.factories.token;

import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.entities.users.User;

import java.time.LocalDateTime;

public class PasswordResetTokenFactoryImp implements PasswordResetTokenFactory {
    @Override
    public PasswordResetToken create(String token, User user, LocalDateTime expiryDate) {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(expiryDate);

        return passwordResetToken;
    }
}
