package com.ichop.core.service.token;

import com.ichop.core.domain.entities.tokens.BaseToken;
import com.ichop.core.domain.entities.tokens.PasswordResetToken;
import com.ichop.core.domain.entities.users.User;
import com.ichop.core.domain.models.service.BaseServiceModel;
import com.ichop.core.domain.models.service.token.PasswordResetTokenServiceModel;
import com.ichop.core.repository.token.TokenRepository;
import com.ichop.core.service.BaseService;
import org.modelmapper.ModelMapper;

public abstract class BaseTokenServices<Entity extends BaseToken,Repository extends TokenRepository<Entity>> extends BaseService<Entity,Repository> {


    public BaseTokenServices(ModelMapper modelMapper, Repository repository) {
        super(modelMapper, repository);
    }

    public <UserServiceModel extends BaseServiceModel> PasswordResetTokenServiceModel findTokenByUser(UserServiceModel user){
        User entityUser = super.modelMapper.map(user,User.class);
        PasswordResetToken result = (PasswordResetToken) super.repository.findByUser(entityUser);

        return this.modelMapper.map(result,PasswordResetTokenServiceModel.class);
    }

    public PasswordResetTokenServiceModel findTokenByToken(String token){
        PasswordResetToken result = (PasswordResetToken) super.repository.findByToken(token);

        return this.modelMapper.map(result,PasswordResetTokenServiceModel.class);
    }

}
