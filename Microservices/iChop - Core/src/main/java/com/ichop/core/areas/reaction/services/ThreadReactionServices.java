package com.ichop.core.areas.reaction.services;

import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.models.binding.ThreadReactionCreateBindingModel;
import com.ichop.core.areas.reaction.domain.models.service.ThreadReactionServiceModel;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface ThreadReactionServices {

    /*
     *
     * Creates thread reaction.
     * @throws UserNotFoundException if the user is null
     * @throws ThreadNotFoundException if the thread is null
     * @throws UserAlreadyReacted if the user already reacted at this thread
     * @returns ThreadReactionServiceModel which is always valid
     *
     * */
    ThreadReactionServiceModel create(ThreadReactionCreateBindingModel bindingModel);

    /*
     *
     * Check if the user has liked the provided thread.
     * @throws UserNotFoundException if the user is null
     * @throws ThreadNotFoundException if the thread is null
     *
     * */
    boolean isReactedByUser(UserServiceModel user, ThreadServiceModel thread);


    /*
     *
     * Finds the total reactions that user has given by their type
     * @throws UserNotFoundException() if the user is null
     *
     * */
    int findTotalReactionsByUserAndType(UserServiceModel user, ReactionType reactionType);
}
