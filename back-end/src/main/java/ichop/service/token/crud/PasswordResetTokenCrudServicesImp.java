package ichop.service.token.crud;

import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.PasswordResetTokenServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.repository.token.PasswordResetTokenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenCrudServicesImp implements PasswordResetTokenCrudServices {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PasswordResetTokenCrudServicesImp(PasswordResetTokenRepository passwordResetTokenRepository, ModelMapper modelMapper) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PasswordResetTokenServiceModel getTokenByUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);
        PasswordResetToken passwordResetToken = this.passwordResetTokenRepository.findByUser(user);

        if (passwordResetToken != null) {
            return this.modelMapper.map(passwordResetToken, PasswordResetTokenServiceModel.class);
        }

        return null;
    }

    @Override
    public PasswordResetTokenServiceModel getTokenByToken(String token) {
        PasswordResetToken passwordResetToken = this.passwordResetTokenRepository.findByToken(token);

        if (passwordResetToken != null) {
            return this.modelMapper.map(passwordResetToken, PasswordResetTokenServiceModel.class);
        }

        return null;
    }

    @Override
    public PasswordResetTokenServiceModel save(PasswordResetTokenServiceModel passwordResetTokenServiceModel) {
        PasswordResetToken passwordResetToken = this.modelMapper.map(passwordResetTokenServiceModel,PasswordResetToken.class);
        return this.modelMapper.map(this.passwordResetTokenRepository.save(passwordResetToken),PasswordResetTokenServiceModel.class);
    }

    @Override
    public void delete(PasswordResetTokenServiceModel passwordResetTokenServiceModel) {
        PasswordResetToken passwordResetToken = this.modelMapper.map(passwordResetTokenServiceModel,PasswordResetToken.class);
        this.passwordResetTokenRepository.delete(passwordResetToken);
    }
}
