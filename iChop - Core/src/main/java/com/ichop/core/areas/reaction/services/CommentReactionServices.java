package com.ichop.core.areas.reaction.services;

import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.models.binding.CommentReactionCreateBindingModel;
import com.ichop.core.areas.reaction.domain.models.service.CommentReactionServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface CommentReactionServices {

    /*
    *
    * Creates comment reaction.
    * @throws CommentNotFoundException if the comment is null
    * @throws UserNotFoundException if the user is null
    * @throws UserAlreadyReacted if the user already reacted
    * @throws CantReactException if the user is creator of the comment
    * @returns CommentReactionServiceModel which is always valid
    *
    * */
    CommentReactionServiceModel create(CommentReactionCreateBindingModel bindingModel);

    /*
    *
    * Check if the user has liked the provided comment.
    * @throws UserNotFoundException if the user is null
    * @throws CommentNotFoundException if the comment is null
    *
    * */
    boolean isLikedByUser(UserServiceModel user, CommentServiceModel comment);


    /*
    *
    * Finds the total reactions that user has given by their type
    * @throws UserNotFoundException() if the user is null
    *
    * */
    int findTotalReactionsByUserAndType(UserServiceModel user, ReactionType reactionType);

}
