package com.ichop.core.service.comment;

import com.ichop.core.domain.models.binding.comment.CommentCreateBindingModel;
import com.ichop.core.domain.models.service.comment.CommentServiceModel;
import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;

public interface CommentServices {

    CommentServiceModel createComment(CommentCreateBindingModel commentCreateBindingModel, UserServiceModel user, ThreadServiceModel thread);

    int getTotalCommentsOfUser(UserServiceModel user);

    CommentServiceModel findCommentById(String commentId);

    void deleteComment(String commentId);
}
