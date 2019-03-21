package com.ichop.core.service.post;

import com.ichop.core.domain.models.binding.post.PostCreateBindingModel;
import com.ichop.core.domain.models.service.post.PostServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;

import java.util.List;

public interface PostServices {


    PostServiceModel createPost(UserServiceModel user, UserServiceModel creator , PostCreateBindingModel postCreateBindingModel);

    List<PostServiceModel> findPostsByUser(UserServiceModel user);

    PostServiceModel findPostById(String postId);

    void deletePost(PostServiceModel postServiceModel);
}
