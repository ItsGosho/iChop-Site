package ichop.service.thread;

import ichop.domain.entities.users.User;
import ichop.domain.models.binding.thread.CommentCreateBindingModel;
import ichop.domain.models.service.CommentServiceModel;
import ichop.domain.models.service.ThreadServiceModel;
import ichop.domain.models.service.UserServiceModel;

public interface CommentServices {

    CommentServiceModel addComment(CommentCreateBindingModel commentCreateBindingModel, UserServiceModel userServiceModel, ThreadServiceModel threadServiceModel);

}
