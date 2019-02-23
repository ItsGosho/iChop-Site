package ichop.service.token;

import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.PasswordResetTokenServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.exceptions.user.UserException;
import ichop.exceptions.user.UserExceptionMessages;
import ichop.repository.token.PasswordResetTokenRepository;
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
        PasswordResetTokenServiceModel passwordResetTokenServiceModel = this.passwordResetTokenCrudServices.getTokenByToken(token);

        if (passwordResetTokenServiceModel == null) {
            return false;
        }

        PasswordResetToken passwordResetToken = this.modelMapper.map(passwordResetTokenServiceModel,PasswordResetToken.class);


        if (passwordResetToken.getExpiryDate().compareTo(LocalDateTime.now()) < 0) {
            return false;
        }

        return true;
    }

    @Override
    public PasswordResetTokenServiceModel createToken(UserServiceModel userServiceModel) {

        if (userServiceModel == null) {
            throw new UserException(UserExceptionMessages.NULL);
        }

        User user = this.modelMapper.map(userServiceModel,User.class);

        this.deleteOldestToken(userServiceModel);

        String token = UUID.randomUUID().toString();

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(LocalDateTime.now().plusSeconds(PasswordResetToken.getTokenExpiration()));
        passwordResetToken.setToken(token);

        this.passwordResetTokenCrudServices.save(this.modelMapper.map(passwordResetToken,PasswordResetTokenServiceModel.class));

        return this.modelMapper.map(passwordResetToken, PasswordResetTokenServiceModel.class);
    }

    @Override
    public void deleteOldestToken(UserServiceModel userServiceModel) {
        PasswordResetTokenServiceModel passwordResetTokenServiceModel = this.passwordResetTokenCrudServices.getTokenByUser(userServiceModel);
        if (passwordResetTokenServiceModel != null) {
            this.passwordResetTokenCrudServices.delete(passwordResetTokenServiceModel);
        }
    }
}
