package com.ichop.core.areas.reaction.domain.models.service;

import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.base.BaseServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadReactionServiceModel extends BaseServiceModel {

    private UserServiceModel user;
    private ReactionType reactionType;
    private ThreadServiceModel thread;
    private LocalDateTime reactionDate;

}
