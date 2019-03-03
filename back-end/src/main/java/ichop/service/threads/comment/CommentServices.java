package ichop.service.threads.comment;

import ichop.domain.models.binding.thread.CommentCreateBindingModel;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface CommentServices {

    CommentServiceModel addComment(CommentCreateBindingModel commentCreateBindingModel, UserServiceModel userServiceModel, ThreadServiceModel threadServiceModel);

}
