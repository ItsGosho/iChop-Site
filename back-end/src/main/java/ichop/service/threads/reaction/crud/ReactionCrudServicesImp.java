package ichop.service.threads.reaction.crud;

import ichop.domain.entities.threads.Comment;
import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.threads.reaction.CommentReaction;
import ichop.domain.entities.base.ReactionType;
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

import java.util.List;

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
    public ThreadReactionServiceModel save(ThreadReactionServiceModel threadReaction) {
        ThreadReaction entityThreadReaction = this.modelMapper.map(threadReaction,ThreadReaction.class);

        this.threadReactionRepository.save(entityThreadReaction);

        return this.modelMapper.map(entityThreadReaction,ThreadReactionServiceModel.class);
    }

    @Override
    public CommentReactionServiceModel save(CommentReactionServiceModel commentReaction) {
        CommentReaction entityCommentReaction = this.modelMapper.map(commentReaction,CommentReaction.class);

        this.commentReactionRepository.save(entityCommentReaction);

        return this.modelMapper.map(entityCommentReaction,CommentReactionServiceModel.class);
    }

    @Override
    public boolean isUserLikedThatThread(UserServiceModel user, ThreadServiceModel thread) {
        User entityUser = this.modelMapper.map(user,User.class);
        Thread entityThread = this.modelMapper.map(thread, Thread.class);

        boolean result = this.threadReactionRepository.isUserLikedThatThread(entityUser,entityThread);

        return result;
    }

    @Override
    public boolean isUserLikedThatComment(UserServiceModel user, CommentServiceModel comment) {
        User entityUser = this.modelMapper.map(user,User.class);
        Comment entityComment = this.modelMapper.map(comment, Comment.class);

        boolean result = this.commentReactionRepository.isUserLikedThatComment(entityUser,entityComment);

        return result;
    }

    @Override
    public int getUserTotalThreadReactions(UserServiceModel user, ReactionType reactionType) {
        User entityUser = this.modelMapper.map(user,User.class);
        return this.threadReactionRepository.getUserTotalReactions(entityUser,reactionType);
    }

    @Override
    public int getUserTotalCommentReactions(UserServiceModel user, ReactionType reactionType) {
        User entityUser = this.modelMapper.map(user,User.class);
        return this.commentReactionRepository.getUserTotalReactions(entityUser,reactionType);
    }

    @Override
    public int getUserTotalReactions(UserServiceModel user, ReactionType reactionType) {
        return this.getUserTotalThreadReactions(user,reactionType) + this.getUserTotalCommentReactions(user,reactionType);
    }

    @Override
    public int getUserTotalReactions(UserServiceModel user) {
        User entityUser = this.modelMapper.map(user,User.class);
        return this.commentReactionRepository.getUserTotalReactions(entityUser) + this.threadReactionRepository.getUserTotalReactions(entityUser);
    }
}
