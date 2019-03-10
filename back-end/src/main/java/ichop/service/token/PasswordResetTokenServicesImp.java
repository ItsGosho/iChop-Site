package ichop.service.token;

import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.token.PasswordResetTokenServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.service.token.crud.PasswordResetTokenCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenServicesImp implements PasswordResetTokenServices {

    private final PasswordResetTokenCrudServices passwordResetTokenCrudServices;
    private final ModelMapper modelMapper;

    @Autowired
    public PasswordResetTokenServicesImp(PasswordResetTokenCrudServices passwordResetTokenCrudServices, ModelMapper modelMapper) {
        this.passwordResetTokenCrudServices = passwordResetTokenCrudServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isValid(String token) {
        PasswordResetTokenServiceModel passwordResetToken = this.passwordResetTokenCrudServices.getTokenByToken(token);

        if (passwordResetToken == null) {
            return false;
        }

        boolean isDateExpired = passwordResetToken.getExpiryDate().compareTo(LocalDateTime.now()) < 0;

        if (isDateExpired) {
            return false;
        }

        return true;
    }

    @Override
    public PasswordResetTokenServiceModel createToken(UserServiceModel user) {

        this.deleteOldestToken(user);

        PasswordResetTokenServiceModel passwordResetToken = new PasswordResetTokenServiceModel();
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(LocalDateTime.now().plusSeconds(PasswordResetToken.getTokenExpiration()));
        passwordResetToken.setToken(UUID.randomUUID().toString());

        PasswordResetTokenServiceModel result = this.passwordResetTokenCrudServices.save(passwordResetToken);

        return result;
    }

    @Override
    public void deleteOldestToken(UserServiceModel user) {

        PasswordResetTokenServiceModel passwordResetTokenServiceModel = this.passwordResetTokenCrudServices.getTokenByUser(user);

        if (passwordResetTokenServiceModel != null) {
            this.passwordResetTokenCrudServices.delete(passwordResetTokenServiceModel);
        }

    }

}
