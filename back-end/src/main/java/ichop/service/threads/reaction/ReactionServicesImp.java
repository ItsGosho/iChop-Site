package ichop.service.threads.reaction;

import ichop.domain.entities.threads.Comment;
import ichop.domain.entities.threads.reaction.CommentReaction;
import ichop.domain.entities.base.ReactionType;
import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.threads.reaction.ThreadReaction;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.threads.comment.CommentReactionServiceModel;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.thread.ThreadReactionServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.thread.CommentNotFoundException;
import ichop.exceptions.thread.ThreadNotFoundException;
import ichop.exceptions.user.UserAlreadyReacted;
import ichop.service.threads.reaction.crud.ReactionCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReactionServicesImp implements ReactionServices {

    private final ReactionCrudServices reactionCrudServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ReactionServicesImp(ReactionCrudServices reactionCrudServices, ModelMapper modelMapper) {
        this.reactionCrudServices = reactionCrudServices;
        this.modelMapper = modelMapper;
    }


    @Override
    public ThreadReactionServiceModel addReaction(ThreadServiceModel threadServiceModel, UserServiceModel userServiceModel, ReactionType reactionType) {

        if(threadServiceModel == null){
            throw new ThreadNotFoundException();
        }

        if (this.reactionCrudServices.isUserLikedThatThread(userServiceModel, threadServiceModel)) {
            throw new UserAlreadyReacted();
        }

        Thread thread = this.modelMapper.map(threadServiceModel, Thread.class);
        User user = this.modelMapper.map(userServiceModel, User.class);

        ThreadReaction threadReaction = new ThreadReaction();
        threadReaction.setReactionType(reactionType);
        threadReaction.setUser(user);
        threadReaction.setThread(thread);
        threadReaction.setReactionDate(LocalDateTime.now());

        ThreadReactionServiceModel resultedReaction = this.reactionCrudServices.save(this.modelMapper.map(threadReaction, ThreadReactionServiceModel.class));

        return resultedReaction;
    }

    @Override
    public CommentReactionServiceModel addReaction(CommentServiceModel commentServiceModel, UserServiceModel userServiceModel, ReactionType reactionType) {

        if(commentServiceModel == null){
            throw new CommentNotFoundException();
        }

        if (this.reactionCrudServices.isUserLikedThatComment(userServiceModel, commentServiceModel)) {
            throw new UserAlreadyReacted();
        }

        Comment comment = this.modelMapper.map(commentServiceModel, Comment.class);
        User user = this.modelMapper.map(userServiceModel, User.class);

        CommentReaction commentReaction = new CommentReaction();
        commentReaction.setReactionType(reactionType);
        commentReaction.setUser(user);
        commentReaction.setComment(comment);
        commentReaction.setReactionDate(LocalDateTime.now());

        CommentReactionServiceModel resultedReaction = this.reactionCrudServices.save(this.modelMapper.map(commentReaction, CommentReactionServiceModel.class));

        return resultedReaction;
    }
}
