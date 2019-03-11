package ichop.service.reaction;

import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.models.service.reaction.CommentReactionServiceModel;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.reaction.ThreadReactionServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.thread.CommentNotFoundException;
import ichop.exceptions.thread.ThreadNotFoundException;
import ichop.exceptions.user.UserAlreadyReacted;
import ichop.service.reaction.crud.ReactionCrudServices;
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
