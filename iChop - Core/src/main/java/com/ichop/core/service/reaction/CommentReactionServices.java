package com.ichop.core.service.reaction;

import com.ichop.core.domain.entities.reaction.ReactionType;
import com.ichop.core.domain.models.service.comment.CommentServiceModel;
import com.ichop.core.domain.models.service.reaction.CommentReactionServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;

public interface CommentReactionServices {

    CommentReactionServiceModel createReaction(CommentServiceModel comment, UserServiceModel user, ReactionType reactionType);

    boolean isCommentLikedByUser(UserServiceModel user, CommentServiceModel comment);
    int findTotalCommentReactionsByUserAndType(UserServiceModel user, ReactionType reactionType);

}
