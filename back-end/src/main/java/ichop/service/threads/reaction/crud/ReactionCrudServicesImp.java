package ichop.service.threads.reaction.crud;

import ichop.domain.entities.threads.Comment;
import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.threads.reaction.CommentReaction;
import ichop.domain.entities.threads.reaction.ThreadReaction;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.threads.comment.CommentReactionServiceModel;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.thread.ThreadReactionServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.repository.threads.reaction.CommentReactionRepository;
import ichop.repository.threads.reaction.ThreadReactionRepository;
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
    public CommentReactionServiceModel save(CommentReactionServiceModel commentReactionServiceModel) {
        CommentReaction commentReaction = this.modelMapper.map(commentReactionServiceModel,CommentReaction.class);

        this.commentReactionRepository.save(commentReaction);

        return this.modelMapper.map(commentReaction,CommentReactionServiceModel.class);
    }

    @Override
    public boolean isUserLikedThatThread(UserServiceModel userServiceModel, ThreadServiceModel threadServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);
        Thread thread = this.modelMapper.map(threadServiceModel, Thread.class);

        boolean result = this.threadReactionRepository.isUserLikedThatThread(user,thread);

        return result;
    }

    @Override
    public boolean isUserLikedThatComment(UserServiceModel userServiceModel, CommentServiceModel commentServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);
        Comment comment = this.modelMapper.map(commentServiceModel, Comment.class);

        boolean result = this.commentReactionRepository.isUserLikedThatComment(user,comment);

        return result;
    }
}
