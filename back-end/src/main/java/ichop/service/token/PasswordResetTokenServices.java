package ichop.service.token;

import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.PasswordResetTokenServiceModel;
import ichop.domain.models.service.UserServiceModel;

public interface PasswordResetTokenServices {

    boolean isValid(String token);

    PasswordResetTokenServiceModel createToken(UserServiceModel userServiceModel);

    void deleteOldestToken(UserServiceModel userServiceModel);
}
