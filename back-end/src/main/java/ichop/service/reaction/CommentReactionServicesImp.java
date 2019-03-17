package ichop.service.reaction;

import ichop.domain.entities.comment.Comment;
import ichop.domain.entities.reaction.CommentReaction;
import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.reaction.CommentReactionServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.thread.CommentNotFoundException;
import ichop.exceptions.user.UserAlreadyReacted;
import ichop.repository.reaction.CommentReactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentReactionServicesImp extends BaseReactionServices<CommentReaction, CommentReactionRepository> implements CommentReactionServices {

    @Autowired
    public CommentReactionServicesImp(ModelMapper modelMapper, CommentReactionRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public CommentReactionServiceModel createReaction(CommentServiceModel comment, UserServiceModel user, ReactionType reactionType) {
        if (comment == null) {
            throw new CommentNotFoundException();
        }

        if (this.isCommentLikedByUser(user, comment)) {
            throw new UserAlreadyReacted();
        }

        CommentReactionServiceModel commentReaction = new CommentReactionServiceModel();
        commentReaction.setReactionType(reactionType);
        commentReaction.setUser(user);
        commentReaction.setComment(comment);
        commentReaction.setReactionDate(LocalDateTime.now());

        CommentReactionServiceModel result = this.save(commentReaction, CommentReactionServiceModel.class);

        return result;
    }

    @Override
    public boolean isCommentLikedByUser(UserServiceModel user, CommentServiceModel comment) {
        User entityUser = this.modelMapper.map(user, User.class);
        Comment entityComment = this.modelMapper.map(comment, Comment.class);

        boolean result = super.repository.isUserLikedThatComment(entityUser, entityComment);

        return result;
    }

    @Override
    public int findTotalCommentReactionsByUserAndType(UserServiceModel user, ReactionType reactionType) {
        User entityUser = this.modelMapper.map(user, User.class);
        return super.repository.getUserTotalReactions(entityUser, reactionType);
    }
}
