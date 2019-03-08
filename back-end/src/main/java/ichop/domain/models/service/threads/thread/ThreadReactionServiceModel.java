package ichop.domain.models.service.threads.thread;

import ichop.domain.entities.base.ReactionType;
import ichop.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadReactionServiceModel {

    private String id;
    private UserServiceModel user;
    private ReactionType reactionType;
    private ThreadServiceModel thread;
    private LocalDateTime reactionDate;

}
