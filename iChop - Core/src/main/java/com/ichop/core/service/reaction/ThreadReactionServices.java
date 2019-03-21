package com.ichop.core.service.reaction;

import com.ichop.core.domain.entities.reaction.ReactionType;
import com.ichop.core.domain.models.service.reaction.ThreadReactionServiceModel;
import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;

public interface ThreadReactionServices {

    ThreadReactionServiceModel createReaction(ThreadServiceModel thread, UserServiceModel user, ReactionType reactionType);

    boolean isThreadLikedByUser(UserServiceModel user, ThreadServiceModel thread);
    int findTotalThreadReactionsByUserAndType(UserServiceModel user, ReactionType reactionType);
}
