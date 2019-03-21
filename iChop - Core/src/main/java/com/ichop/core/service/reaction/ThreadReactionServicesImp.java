package com.ichop.core.service.reaction;

import com.ichop.core.domain.entities.reaction.ReactionType;
import com.ichop.core.domain.entities.reaction.ThreadReaction;
import com.ichop.core.domain.entities.threads.Thread;
import com.ichop.core.domain.entities.users.User;
import com.ichop.core.domain.models.service.reaction.ThreadReactionServiceModel;
import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.exceptions.thread.ThreadNotFoundException;
import com.ichop.core.exceptions.user.UserAlreadyReacted;
import com.ichop.core.repository.reaction.ThreadReactionRepository;
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
