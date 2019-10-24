package ichop.core.areas.post.services;

import ichop.core.areas.post.domain.models.binding.PostCreateBindingModel;
import ichop.core.areas.post.domain.models.service.PostServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;

import java.util.List;

public interface PostServices {


    /*
    *
    * Creates a entity of type Post.
    * @param bindingModel represent the needed data to create a post
    * @throws UserNotFoundException if the user or the creator are null
    * @returns PostServiceModel which is always valid
    *
    * */
    PostServiceModel create(PostCreateBindingModel bindingModel);


    /*
    *
    * Finds all of the posts that a user has been created.
    * @throws UserNotFoundException if the user is null
    *
    * */
    List<PostServiceModel> findByUser(UserServiceModel user);


    /*
    *
    * Finds a post by his id
    * @returns PostServiceModel which will be null if the post isn't found
    *
    * */
    PostServiceModel findById(String postId);


    /*
    *
    * Delete a post
    * @throws PostNotFoundException in case the post is null or cannot be found
    *
    * */
    void deleteByModel(PostServiceModel postServiceModel);
}
