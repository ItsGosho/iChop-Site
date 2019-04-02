package com.ichop.core.areas.reaction.services;

import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.reaction.domain.models.service.CommentReactionServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface CommentReactionServices {

    CommentReactionServiceModel createReaction(CommentServiceModel comment, UserServiceModel user, ReactionType reactionType);

    boolean isCommentLikedByUser(UserServiceModel user, CommentServiceModel comment);
    int findTotalCommentReactionsByUserAndType(UserServiceModel user, ReactionType reactionType);

    boolean isReactedByUser(CommentServiceModel comment,UserServiceModel user);

}
