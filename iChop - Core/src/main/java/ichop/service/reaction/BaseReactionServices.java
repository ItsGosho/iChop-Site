package ichop.service.reaction;

import ichop.domain.entities.reaction.BaseReaction;
import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.BaseServiceModel;
import ichop.repository.reaction.ReactionRepository;
import ichop.service.BaseService;
import org.modelmapper.ModelMapper;

public abstract class BaseReactionServices<Entity extends BaseReaction,Repository extends ReactionRepository<Entity>> extends BaseService<Entity, Repository> {


    public BaseReactionServices(ModelMapper modelMapper, Repository repository) {
        super(modelMapper, repository);
    }

    public <UserServiceModel extends BaseServiceModel> int findTotalReactionsByUser(UserServiceModel user, ReactionType reactionType){
        User entityUser = this.modelMapper.map(user,User.class);
        int result = this.repository.getUserTotalReactions(entityUser,reactionType);

        return result;
    }

    public <UserServiceModel extends BaseServiceModel> int findTotalReactionsByUser(UserServiceModel user){
        User entityUser = this.modelMapper.map(user,User.class);
        int result = this.repository.getUserTotalReactions(entityUser);

        return result;
    }
}
