package ichop.domain.models.service.threads.thread;

import ichop.domain.entities.threads.reaction.ReactionType;
import ichop.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadReactionServiceModel {

    private String id;
    private UserServiceModel user;
    private ReactionType reactionType;
    private ThreadServiceModel thread;
}
