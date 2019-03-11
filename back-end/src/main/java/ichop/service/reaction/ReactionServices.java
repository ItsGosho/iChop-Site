package ichop.service.reaction;

import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.models.service.reaction.CommentReactionServiceModel;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.reaction.ThreadReactionServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface ReactionServices {
    ThreadReactionServiceModel addReaction(ThreadServiceModel thread, UserServiceModel user, ReactionType reactionType);

    CommentReactionServiceModel addReaction(CommentServiceModel comment, UserServiceModel user, ReactionType reactionType);

}
