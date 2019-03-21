package com.ichop.core.service.token;

import com.ichop.core.domain.entities.tokens.PasswordResetToken;
import com.ichop.core.domain.models.service.token.PasswordResetTokenServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.repository.token.PasswordResetTokenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenServicesImp extends BaseTokenServices<PasswordResetToken, PasswordResetTokenRepository> implements PasswordResetTokenServices {


    @Autowired
    public PasswordResetTokenServicesImp(ModelMapper modelMapper, PasswordResetTokenRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public boolean isTokenValid(String token) {
        PasswordResetTokenServiceModel passwordResetToken = super.findTokenByToken(token);

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

        this.deleteOldestTokenByUser(user);

        PasswordResetTokenServiceModel passwordResetToken = new PasswordResetTokenServiceModel();
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(LocalDateTime.now().plusSeconds(PasswordResetToken.getTokenExpiration()));
        passwordResetToken.setToken(UUID.randomUUID().toString());

        PasswordResetTokenServiceModel result = super.save(passwordResetToken,PasswordResetTokenServiceModel.class);

        return result;
    }

    @Override
    public void deleteOldestTokenByUser(UserServiceModel user) {

        PasswordResetTokenServiceModel passwordResetTokenServiceModel = super.findTokenByUser(user);

        if (passwordResetTokenServiceModel != null) {
            super.delete(passwordResetTokenServiceModel);
        }

    }

    @Override
    public PasswordResetTokenServiceModel findTokenByToken(String token) {
        return super.findTokenByToken(token);
    }
}
