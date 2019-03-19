package ichop.service.reaction;

import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.models.service.reaction.ThreadReactionServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface ThreadReactionServices {

    ThreadReactionServiceModel createReaction(ThreadServiceModel thread, UserServiceModel user, ReactionType reactionType);

    boolean isThreadLikedByUser(UserServiceModel user, ThreadServiceModel thread);
    int findTotalThreadReactionsByUserAndType(UserServiceModel user, ReactionType reactionType);
}
