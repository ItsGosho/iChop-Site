package ichop.service.comment;

import ichop.domain.models.binding.comment.CommentCreateBindingModel;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface CommentServices {

    CommentServiceModel createComment(CommentCreateBindingModel commentCreateBindingModel, UserServiceModel user, ThreadServiceModel thread);

    int getTotalCommentsOfUser(UserServiceModel user);

    CommentServiceModel findCommentById(String commentId);

    void deleteComment(String commentId);
}
