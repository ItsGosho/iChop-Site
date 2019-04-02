package com.ichop.core.areas.token.services;

import com.ichop.core.areas.token.domain.entities.PasswordResetToken;
import com.ichop.core.areas.token.domain.models.service.PasswordResetTokenServiceModel;
import com.ichop.core.areas.token.repositories.PasswordResetTokenRepository;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
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
    public boolean isValid(String token) {
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
    public PasswordResetTokenServiceModel create(UserServiceModel user) {

        this.deleteOldestByUser(user);

        PasswordResetTokenServiceModel passwordResetToken = new PasswordResetTokenServiceModel();
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(LocalDateTime.now().plusSeconds(PasswordResetToken.getTokenExpiration()));
        passwordResetToken.setToken(UUID.randomUUID().toString());

        PasswordResetTokenServiceModel result = super.save(passwordResetToken,PasswordResetTokenServiceModel.class);

        return result;
    }

    @Override
    public void deleteOldestByUser(UserServiceModel user) {

        PasswordResetTokenServiceModel passwordResetTokenServiceModel = super.findTokenByUser(user);

        if (passwordResetTokenServiceModel != null) {
            super.delete(passwordResetTokenServiceModel);
        }

    }

    @Override
    public PasswordResetTokenServiceModel findByToken(String token) {
        return super.findTokenByToken(token);
    }
}
