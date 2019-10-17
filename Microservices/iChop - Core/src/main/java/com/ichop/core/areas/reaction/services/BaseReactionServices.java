package com.ichop.core.areas.reaction.services;

import com.ichop.core.areas.reaction.domain.entities.BaseReaction;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.repositories.ReactionRepository;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.base.BaseService;
import com.ichop.core.base.BaseServiceModel;
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
