package ichop.service.threads.comment.crud;

import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

import java.util.List;

public interface CommentCrudServices {

    int getTotalCommentsOfUser(UserServiceModel user);
    CommentServiceModel save(CommentServiceModel comment);

    CommentServiceModel getById(String id);
    void delete(CommentServiceModel comment);
    void delete(String id);
}
