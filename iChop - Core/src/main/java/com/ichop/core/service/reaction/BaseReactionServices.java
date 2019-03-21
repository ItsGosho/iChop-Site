package com.ichop.core.service.reaction;

import com.ichop.core.domain.entities.reaction.BaseReaction;
import com.ichop.core.domain.entities.reaction.ReactionType;
import com.ichop.core.domain.entities.users.User;
import com.ichop.core.domain.models.service.BaseServiceModel;
import com.ichop.core.repository.reaction.ReactionRepository;
import com.ichop.core.service.BaseService;
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
