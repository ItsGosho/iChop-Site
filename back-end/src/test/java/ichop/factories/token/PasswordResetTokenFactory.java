package ichop.factories.token;

import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.entities.users.User;

import java.time.LocalDateTime;

public interface PasswordResetTokenFactory {

    PasswordResetToken create(String token, User user, LocalDateTime expiryDate);
}
