package ichop.service.threads.comment.crud;

import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface CommentCrudServices {

    int getTotalCommentsOfUser(UserServiceModel userServiceModel);
    CommentServiceModel save(CommentServiceModel commentServiceModel);

    CommentServiceModel getById(String id);
    void delete(CommentServiceModel commentServiceModel);
    void delete(String id);
}
