package ichop.service.token;

import ichop.domain.models.service.token.PasswordResetTokenServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface PasswordResetTokenServices {

    boolean isValid(String token);

    PasswordResetTokenServiceModel createToken(UserServiceModel userServiceModel);

    void deleteOldestToken(UserServiceModel userServiceModel);
}
