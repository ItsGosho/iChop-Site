package ichop.service.thread.crud;

import ichop.domain.models.service.UserServiceModel;

public interface CommentCrudServices {

    int getTotalCommentsOfUser(UserServiceModel userServiceModel);

}
