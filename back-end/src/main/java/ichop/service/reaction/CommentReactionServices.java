package ichop.service.reaction;

import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.reaction.CommentReactionServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface CommentReactionServices {

    CommentReactionServiceModel createReaction(CommentServiceModel comment, UserServiceModel user, ReactionType reactionType);

    boolean isCommentLikedByUser(UserServiceModel user, CommentServiceModel comment);
    int findTotalCommentReactionsByUserAndType(UserServiceModel user, ReactionType reactionType);

}
