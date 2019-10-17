package com.ichop.core.areas.reaction.domain.models.service;

import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.base.BaseServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentReactionServiceModel extends BaseServiceModel {

    private UserServiceModel user;
    private ReactionType reactionType;
    private CommentServiceModel comment;
    private LocalDateTime reactionDate;
}
