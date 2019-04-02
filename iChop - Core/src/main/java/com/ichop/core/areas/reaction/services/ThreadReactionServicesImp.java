package com.ichop.core.areas.reaction.services;

import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.entities.ThreadReaction;
import com.ichop.core.areas.reaction.domain.models.service.ThreadReactionServiceModel;
import com.ichop.core.areas.reaction.repositories.ThreadReactionRepository;
import com.ichop.core.areas.thread.domain.entities.Thread;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserAlreadyReacted;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ThreadReactionServicesImp extends BaseReactionServices<ThreadReaction, ThreadReactionRepository> implements ThreadReactionServices {

    @Autowired
    public ThreadReactionServicesImp(ModelMapper modelMapper, ThreadReactionRepository repository) {
        super(modelMapper, repository);
    }


    @Override
    public ThreadReactionServiceModel createReaction(ThreadServiceModel thread, UserServiceModel user, ReactionType reactionType) {

        if(user == null){
            throw new UserNotFoundException();
        }

        if(thread == null){
            throw new ThreadNotFoundException();
        }

        if (this.isThreadLikedByUser(user, thread)) {
            throw new UserAlreadyReacted();
        }

        ThreadReactionServiceModel threadReaction = new ThreadReactionServiceModel();
        threadReaction.setReactionType(reactionType);
        threadReaction.setUser(user);
        threadReaction.setThread(thread);
        threadReaction.setReactionDate(LocalDateTime.now());

        ThreadReactionServiceModel result = super.save(threadReaction,ThreadReactionServiceModel.class);

        return result;
    }

    @Override
    public boolean isThreadLikedByUser(UserServiceModel user, ThreadServiceModel thread) {
        User entityUser = this.modelMapper.map(user,User.class);
        Thread entityThread = this.modelMapper.map(thread, Thread.class);

        boolean result = super.repository.isUserLikedThatThread(entityUser,entityThread);

        return result;
    }

    @Override
    public int findTotalThreadReactionsByUserAndType(UserServiceModel user, ReactionType reactionType) {
        User entityUser = this.modelMapper.map(user,User.class);
        return super.repository.getUserTotalReactions(entityUser,reactionType);
    }
}
