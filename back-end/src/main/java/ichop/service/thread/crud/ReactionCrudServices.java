package ichop.service.thread.crud;

import ichop.domain.models.service.ThreadReactionServiceModel;
import ichop.domain.models.service.ThreadServiceModel;
import ichop.domain.models.service.UserServiceModel;

public interface ReactionCrudServices {

    ThreadReactionServiceModel save(ThreadReactionServiceModel threadReactionServiceModel);

    boolean isUserLikedThatThread(UserServiceModel userServiceModel, ThreadServiceModel threadServiceModel);
}
