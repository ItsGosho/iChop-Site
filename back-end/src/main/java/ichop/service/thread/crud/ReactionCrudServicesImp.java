package ichop.service.thread.crud;

import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.threads.reaction.ThreadReaction;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.ThreadReactionServiceModel;
import ichop.domain.models.service.ThreadServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.repository.thread.reaction.CommentReactionRepository;
import ichop.repository.thread.reaction.ThreadReactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactionCrudServicesImp implements ReactionCrudServices {

    private final ThreadReactionRepository threadReactionRepository;
    private final CommentReactionRepository commentReactionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReactionCrudServicesImp(ThreadReactionRepository threadReactionRepository, CommentReactionRepository commentReactionRepository, ModelMapper modelMapper) {
        this.threadReactionRepository = threadReactionRepository;
        this.commentReactionRepository = commentReactionRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ThreadReactionServiceModel save(ThreadReactionServiceModel threadReactionServiceModel) {
        ThreadReaction threadReaction = this.modelMapper.map(threadReactionServiceModel,ThreadReaction.class);

        this.threadReactionRepository.save(threadReaction);

        return this.modelMapper.map(threadReaction,ThreadReactionServiceModel.class);
    }

    @Override
    public boolean isUserLikedThatThread(UserServiceModel userServiceModel, ThreadServiceModel threadServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);
        Thread thread = this.modelMapper.map(threadServiceModel, Thread.class);

        boolean result = this.threadReactionRepository.isUserLikedThatThread(user,thread);

        return result;
    }
}
