package com.ichop.core.areas.reaction.services;

import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.models.binding.CommentReactionCreateBindingModel;
import com.ichop.core.areas.reaction.domain.models.service.CommentReactionServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface CommentReactionServices {

    CommentReactionServiceModel create(CommentReactionCreateBindingModel bindingModel);

    boolean isLikedByUser(UserServiceModel user, CommentServiceModel comment);
    int findTotalReactionsByUserAndType(UserServiceModel user, ReactionType reactionType);

}
