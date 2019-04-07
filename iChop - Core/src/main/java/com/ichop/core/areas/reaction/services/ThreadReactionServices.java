package com.ichop.core.areas.reaction.services;

import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.models.binding.ThreadReactionCreateBindingModel;
import com.ichop.core.areas.reaction.domain.models.service.ThreadReactionServiceModel;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface ThreadReactionServices {

    ThreadReactionServiceModel create(ThreadReactionCreateBindingModel bindingModel);

    boolean isLikedByUser(UserServiceModel user, ThreadServiceModel thread);
    int findTotalReactionsByUserAndType(UserServiceModel user, ReactionType reactionType);
}
