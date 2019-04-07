package com.ichop.core.areas.reaction.domain.models.binding;

import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadReactionCreateBindingModel {

    private UserServiceModel user;
    private ThreadServiceModel thread;
    private ReactionType reactionType;

}
