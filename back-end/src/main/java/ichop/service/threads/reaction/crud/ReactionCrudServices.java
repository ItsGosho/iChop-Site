package ichop.service.threads.reaction.crud;

import ichop.domain.entities.base.ReactionType;
import ichop.domain.entities.threads.reaction.ThreadReaction;
import ichop.domain.models.service.threads.comment.CommentReactionServiceModel;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.thread.ThreadReactionServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

import java.util.List;

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
