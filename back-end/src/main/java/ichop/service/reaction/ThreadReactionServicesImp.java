package ichop.service.reaction;

import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.entities.reaction.ThreadReaction;
import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.reaction.ThreadReactionServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.thread.ThreadNotFoundException;
import ichop.exceptions.user.UserAlreadyReacted;
import ichop.repository.reaction.ThreadReactionRepository;
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
