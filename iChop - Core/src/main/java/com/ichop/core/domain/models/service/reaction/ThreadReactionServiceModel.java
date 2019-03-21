package com.ichop.core.domain.models.service.reaction;

import com.ichop.core.domain.entities.reaction.ReactionType;
import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.domain.models.service.BaseServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
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
