package ichop.core.areas.reaction.domain.models.service;

import ichop.core.areas.reaction.domain.entities.ReactionType;
import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import ichop.core.base.BaseServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
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
