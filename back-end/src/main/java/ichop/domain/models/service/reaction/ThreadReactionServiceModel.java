package ichop.domain.models.service.reaction;

import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.models.service.BaseServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
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
