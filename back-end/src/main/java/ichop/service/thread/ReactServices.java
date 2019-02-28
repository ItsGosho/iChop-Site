package ichop.service.thread;

import ichop.domain.entities.threads.reaction.ReactionType;
import ichop.domain.models.service.ThreadReactionServiceModel;
import ichop.domain.models.service.ThreadServiceModel;
import ichop.domain.models.service.UserServiceModel;

public interface ReactServices {
    ThreadReactionServiceModel addReaction(ThreadServiceModel threadServiceModel, UserServiceModel userServiceModel, ReactionType reactionType);
}
