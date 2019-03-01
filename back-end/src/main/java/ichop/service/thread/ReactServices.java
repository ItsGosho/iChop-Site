package ichop.service.thread;

import ichop.domain.entities.threads.reaction.ReactionType;
import ichop.domain.models.service.*;

public interface ReactServices {
    ThreadReactionServiceModel addReaction(ThreadServiceModel threadServiceModel, UserServiceModel userServiceModel, ReactionType reactionType);

    CommentReactionServiceModel addReaction(CommentServiceModel commentServiceModel, UserServiceModel userServiceModel, ReactionType reactionType);

}
