package ichop.service.token;

import ichop.domain.models.service.token.PasswordResetTokenServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface PasswordResetTokenServices {

    boolean isTokenValid(String token);

    PasswordResetTokenServiceModel createToken(UserServiceModel user);

    void deleteOldestTokenByUser(UserServiceModel user);

    PasswordResetTokenServiceModel findTokenByToken(String token);
}
