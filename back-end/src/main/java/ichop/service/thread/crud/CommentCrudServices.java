package ichop.service.thread.crud;

import ichop.domain.models.binding.thread.CommentCreateBindingModel;
import ichop.domain.models.service.CommentServiceModel;
import ichop.domain.models.service.UserServiceModel;

public interface CommentCrudServices {

    int getTotalCommentsOfUser(UserServiceModel userServiceModel);
    CommentServiceModel save(CommentServiceModel commentServiceModel);

    CommentServiceModel getById(String id);
    void delete(CommentServiceModel commentServiceModel);
}
