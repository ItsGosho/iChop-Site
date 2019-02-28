package ichop.service.thread;

import ichop.domain.entities.threads.reaction.ReactionType;
import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.threads.reaction.ThreadReaction;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.ThreadReactionServiceModel;
import ichop.domain.models.service.ThreadServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.exceptions.user.UserAlreadyLikedThis;
import ichop.service.thread.crud.ReactionCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactServicesImp implements ReactServices {

    private final ReactionCrudServices reactionCrudServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ReactServicesImp(ReactionCrudServices reactionCrudServices, ModelMapper modelMapper) {
        this.reactionCrudServices = reactionCrudServices;
        this.modelMapper = modelMapper;
    }


    @Override
    public ThreadReactionServiceModel addReaction(ThreadServiceModel threadServiceModel, UserServiceModel userServiceModel, ReactionType reactionType) {

        if (this.reactionCrudServices.isUserLikedThatThread(userServiceModel, threadServiceModel)) {
            throw new UserAlreadyLikedThis();
        }

        Thread thread = this.modelMapper.map(threadServiceModel, Thread.class);
        User user = this.modelMapper.map(userServiceModel, User.class);

        ThreadReaction threadReaction = new ThreadReaction();
        threadReaction.setReactionType(reactionType);
        threadReaction.setUser(user);
        threadReaction.setThread(thread);

        ThreadReactionServiceModel resultedReaction = this.reactionCrudServices.save(this.modelMapper.map(threadReaction, ThreadReactionServiceModel.class));

        return resultedReaction;
    }
}
