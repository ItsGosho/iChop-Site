package com.ichop.core.areas.comment.services;

import com.ichop.core.areas.comment.domain.models.binding.CommentCreateBindingModel;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface CommentServices {

    CommentServiceModel create(CommentCreateBindingModel commentCreateBindingModel, UserServiceModel user, ThreadServiceModel thread);

    int getTotalOfUser(UserServiceModel user);

    CommentServiceModel findById(String commentId);

    void delete(String commentId);

    void delete(CommentServiceModel commentServiceModel);
}
