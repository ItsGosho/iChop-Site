package ichop.service.thread.crud;

import ichop.domain.models.service.*;

public interface ReactionCrudServices {

    ThreadReactionServiceModel save(ThreadReactionServiceModel threadReactionServiceModel);
    CommentReactionServiceModel save(CommentReactionServiceModel commentReactionServiceModel);

    boolean isUserLikedThatThread(UserServiceModel userServiceModel, ThreadServiceModel threadServiceModel);

    boolean isUserLikedThatComment(UserServiceModel userServiceModel, CommentServiceModel commentServiceModel);
}
