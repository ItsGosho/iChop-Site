package ichop.service.threads.reaction.crud;

import ichop.domain.entities.base.ReactionType;
import ichop.domain.models.service.threads.comment.CommentReactionServiceModel;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.thread.ThreadReactionServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface ReactionCrudServices {

    ThreadReactionServiceModel save(ThreadReactionServiceModel threadReactionServiceModel);
    CommentReactionServiceModel save(CommentReactionServiceModel commentReactionServiceModel);

    boolean isUserLikedThatThread(UserServiceModel userServiceModel, ThreadServiceModel threadServiceModel);
    boolean isUserLikedThatComment(UserServiceModel userServiceModel, CommentServiceModel commentServiceModel);

    int getUserTotalThreadReactions(UserServiceModel userServiceModel,ReactionType reactionType);
    int getUserTotalCommentReactions(UserServiceModel userServiceModel,ReactionType reactionType);
    int getUserTotalReactions(UserServiceModel userServiceModel,ReactionType reactionType);
    int getUserTotalReactions(UserServiceModel userServiceModel);

}
