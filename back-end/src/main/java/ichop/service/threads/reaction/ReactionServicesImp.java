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
    public ThreadReactionServiceModel addReaction(ThreadServiceModel thread, UserServiceModel user, ReactionType reactionType) {

        if(user == null){
            throw new ThreadNotFoundException();
        }

        if (this.reactionCrudServices.isUserLikedThatThread(user, thread)) {
            throw new UserAlreadyReacted();
        }

        ThreadReactionServiceModel threadReaction = new ThreadReactionServiceModel();
        threadReaction.setReactionType(reactionType);
        threadReaction.setUser(user);
        threadReaction.setThread(thread);
        threadReaction.setReactionDate(LocalDateTime.now());

        ThreadReactionServiceModel result = this.reactionCrudServices.save(threadReaction);

        return result;
    }

    @Override
    public CommentReactionServiceModel addReaction(CommentServiceModel comment, UserServiceModel user, ReactionType reactionType) {

        if(comment == null){
            throw new CommentNotFoundException();
        }

        if (this.reactionCrudServices.isUserLikedThatComment(user, comment)) {
            throw new UserAlreadyReacted();
        }

        CommentReactionServiceModel commentReaction = new CommentReactionServiceModel();
        commentReaction.setReactionType(reactionType);
        commentReaction.setUser(user);
        commentReaction.setComment(comment);
        commentReaction.setReactionDate(LocalDateTime.now());

        CommentReactionServiceModel result = this.reactionCrudServices.save(commentReaction);

        return result;
    }
}
