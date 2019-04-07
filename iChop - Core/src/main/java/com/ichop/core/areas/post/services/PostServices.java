package com.ichop.core.areas.post.services;

import com.ichop.core.areas.post.domain.models.binding.PostCreateBindingModel;
import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

import java.util.List;

public interface PostServices {


    PostServiceModel create(PostCreateBindingModel postCreateBindingModel);

    List<PostServiceModel> findByUser(UserServiceModel user);

    PostServiceModel findById(String postId);

    void deleteByModel(PostServiceModel postServiceModel);
}
