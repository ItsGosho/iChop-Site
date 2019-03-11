package ichop.service.reaction.crud;

import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.models.service.reaction.CommentReactionServiceModel;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.reaction.ThreadReactionServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface ReactionCrudServices {

    ThreadReactionServiceModel save(ThreadReactionServiceModel threadReaction);
    CommentReactionServiceModel save(CommentReactionServiceModel commentReaction);

    boolean isUserLikedThatThread(UserServiceModel user, ThreadServiceModel thread);
    boolean isUserLikedThatComment(UserServiceModel user, CommentServiceModel comment);

    int getUserTotalThreadReactions(UserServiceModel user,ReactionType reactionType);
    int getUserTotalCommentReactions(UserServiceModel user,ReactionType reactionType);
    int getUserTotalReactions(UserServiceModel user,ReactionType reactionType);
    int getUserTotalReactions(UserServiceModel user);

}
