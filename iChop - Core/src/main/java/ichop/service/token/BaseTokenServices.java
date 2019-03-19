package ichop.service.token;

import ichop.domain.entities.tokens.BaseToken;
import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.BaseServiceModel;
import ichop.domain.models.service.token.PasswordResetTokenServiceModel;
import ichop.repository.token.TokenRepository;
import ichop.service.BaseService;
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
