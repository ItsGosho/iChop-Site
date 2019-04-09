package com.ichop.core.areas.token.services;

import com.ichop.core.areas.token.domain.entities.PasswordResetToken;
import com.ichop.core.areas.token.domain.models.binding.PasswordResetTokenCreateBindingModel;
import com.ichop.core.areas.token.domain.models.service.PasswordResetTokenServiceModel;
import com.ichop.core.areas.token.exceptions.TokenNotFoundException;
import com.ichop.core.areas.token.repositories.PasswordResetTokenRepository;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PasswordResetTokenServicesImp extends BaseTokenServices<PasswordResetToken, PasswordResetTokenRepository> implements PasswordResetTokenServices {


    @Autowired
    public PasswordResetTokenServicesImp(ModelMapper modelMapper, PasswordResetTokenRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public boolean isValid(String token) {
        PasswordResetTokenServiceModel passwordResetToken = this.findTokenByToken(token);

        if (passwordResetToken == null) {
            return false;
        }

        boolean isDateExpired = this.isTokenDateExpired(passwordResetToken);

        if (isDateExpired) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isTokenDateExpired(PasswordResetTokenServiceModel passwordResetToken){
        return passwordResetToken.getExpiryDate().compareTo(LocalDateTime.now()) < 0;
    }

    @Override
    public PasswordResetTokenServiceModel create(PasswordResetTokenCreateBindingModel bindingModel) {

        this.deleteOldestByUser(bindingModel.getUser());

        PasswordResetTokenServiceModel passwordResetToken = this.modelMapper.map(bindingModel,PasswordResetTokenServiceModel.class);
        passwordResetToken.setExpiryDate(LocalDateTime.now().plusSeconds(PasswordResetToken.getTokenExpiration()));
        passwordResetToken.setToken(UUID.randomUUID().toString());

        PasswordResetTokenServiceModel result = this.save(passwordResetToken,PasswordResetTokenServiceModel.class);

        return result;
    }

    @Override
    public void deleteOldestByUser(UserServiceModel user) {

        PasswordResetTokenServiceModel passwordResetTokenServiceModel = this.findTokenByUser(user);

        if (passwordResetTokenServiceModel != null) {
            this.delete(passwordResetTokenServiceModel);
        }

    }

    @Override
    public PasswordResetTokenServiceModel findByToken(String token) {
        return this.findTokenByToken(token);
    }

    @Override
    public List<PasswordResetTokenServiceModel> findAllExpired(){
        return this.repository
                .findAll()
                .stream()
                .filter(x-> x.getExpiryDate().isBefore(LocalDateTime.now()))
                .map(x-> this.modelMapper.map(x,PasswordResetTokenServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTokenById(String id){
        if(!this.repository.existsById(id)){
            throw new TokenNotFoundException();
        }

        this.deleteById(id);
    }

}
