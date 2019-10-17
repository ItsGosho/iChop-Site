package com.ichop.core.areas.token.services;

import com.ichop.core.areas.token.domain.entities.BaseToken;
import com.ichop.core.areas.token.domain.entities.PasswordResetToken;
import com.ichop.core.areas.token.domain.models.service.PasswordResetTokenServiceModel;
import com.ichop.core.areas.token.repositories.TokenRepository;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.base.BaseService;
import com.ichop.core.base.BaseServiceModel;
import org.modelmapper.ModelMapper;

import java.time.Clock;

public abstract class BaseTokenServices<Entity extends BaseToken,Repository extends TokenRepository<Entity>> extends BaseService<Entity,Repository> {


    public BaseTokenServices(ModelMapper modelMapper, Repository repository) {
        super(modelMapper, repository);
    }

    public BaseTokenServices(ModelMapper modelMapper, Repository repository, Clock clock) {
        super(modelMapper, repository);
    }

    public <UserServiceModel extends BaseServiceModel> PasswordResetTokenServiceModel findTokenByUser(UserServiceModel user){
        User entityUser = super.modelMapper.map(user,User.class);
        PasswordResetToken result = (PasswordResetToken) super.repository.findByUser(entityUser);

        return result != null ? super.modelMapper.map(result,PasswordResetTokenServiceModel.class) : null;
    }

    public PasswordResetTokenServiceModel findTokenByToken(String token){
        PasswordResetToken result = (PasswordResetToken) super.repository.findByToken(token);

        return result!= null ? super.modelMapper.map(result,PasswordResetTokenServiceModel.class) : null;
    }

}
