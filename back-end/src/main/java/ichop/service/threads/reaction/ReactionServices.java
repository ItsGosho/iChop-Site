package ichop.service.threads.reaction;

import ichop.domain.entities.base.ReactionType;
import ichop.domain.models.service.threads.comment.CommentReactionServiceModel;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.thread.ThreadReactionServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface ReactionServices {
    ThreadReactionServiceModel addReaction(ThreadServiceModel threadServiceModel, UserServiceModel userServiceModel, ReactionType reactionType);

    CommentReactionServiceModel addReaction(CommentServiceModel commentServiceModel, UserServiceModel userServiceModel, ReactionType reactionType);

}
