package ichop.service.token.crud;

import ichop.domain.models.service.token.PasswordResetTokenServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface PasswordResetTokenCrudServices {

    PasswordResetTokenServiceModel getTokenByUser(UserServiceModel userServiceModel);

    PasswordResetTokenServiceModel getTokenByToken(String token);

    PasswordResetTokenServiceModel save(PasswordResetTokenServiceModel passwordResetTokenServiceModel);

    void delete(PasswordResetTokenServiceModel passwordResetTokenServiceModel);
}
