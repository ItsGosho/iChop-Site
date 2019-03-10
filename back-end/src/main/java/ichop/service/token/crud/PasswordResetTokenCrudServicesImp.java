package ichop.service.token.crud;

import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.token.PasswordResetTokenServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
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
    public PasswordResetTokenServiceModel getTokenByUser(UserServiceModel user) {
        User entityUser = this.modelMapper.map(user,User.class);
        PasswordResetToken entitiyPasswordResetToken = this.passwordResetTokenRepository.findByUser(entityUser);

        if (entitiyPasswordResetToken != null) {
            return this.modelMapper.map(entitiyPasswordResetToken, PasswordResetTokenServiceModel.class);
        }

        return null;
    }

    @Override
    public PasswordResetTokenServiceModel getTokenByToken(String token) {
        PasswordResetToken entitiyPasswordResetToken = this.passwordResetTokenRepository.findByToken(token);

        if (entitiyPasswordResetToken != null) {
            return this.modelMapper.map(entitiyPasswordResetToken, PasswordResetTokenServiceModel.class);
        }

        return null;
    }

    @Override
    public PasswordResetTokenServiceModel save(PasswordResetTokenServiceModel passwordResetToken) {
        PasswordResetToken entityPasswordResetToken = this.modelMapper.map(passwordResetToken,PasswordResetToken.class);
        this.passwordResetTokenRepository.save(entityPasswordResetToken);

        return this.modelMapper.map(entityPasswordResetToken,PasswordResetTokenServiceModel.class);
    }

    @Override
    public void delete(PasswordResetTokenServiceModel passwordResetToken) {
        PasswordResetToken entitiyPasswordResetToken = this.modelMapper.map(passwordResetToken,PasswordResetToken.class);
        this.passwordResetTokenRepository.delete(entitiyPasswordResetToken);
    }
}
