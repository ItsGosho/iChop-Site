package ichop.service.token.crud;

import ichop.domain.models.service.PasswordResetTokenServiceModel;
import ichop.domain.models.service.UserServiceModel;

public interface PasswordResetTokenCrudServices {

    PasswordResetTokenServiceModel getTokenByUser(UserServiceModel userServiceModel);

    PasswordResetTokenServiceModel getTokenByToken(String token);

    PasswordResetTokenServiceModel save(PasswordResetTokenServiceModel passwordResetTokenServiceModel);

    void delete(PasswordResetTokenServiceModel passwordResetTokenServiceModel);
}
