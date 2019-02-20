package ichop.service.thread;

import ichop.domain.entities.users.User;
import ichop.domain.models.service.UserServiceModel;

public interface CommentServices {

    int getTotalCommentsOfUser(UserServiceModel userServiceModel);
}
