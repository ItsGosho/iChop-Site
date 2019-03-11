package ichop.service.comment.crud;

import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface CommentCrudServices {

    int getTotalCommentsOfUser(UserServiceModel user);
    CommentServiceModel save(CommentServiceModel comment);

    CommentServiceModel getById(String id);
    void delete(CommentServiceModel comment);
    void delete(String id);
}
