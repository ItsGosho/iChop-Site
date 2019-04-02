package com.ichop.core.areas.reaction.services;

import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.models.service.ThreadReactionServiceModel;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface ThreadReactionServices {

    ThreadReactionServiceModel createReaction(ThreadServiceModel thread, UserServiceModel user, ReactionType reactionType);

    boolean isThreadLikedByUser(UserServiceModel user, ThreadServiceModel thread);
    int findTotalThreadReactionsByUserAndType(UserServiceModel user, ReactionType reactionType);
}
