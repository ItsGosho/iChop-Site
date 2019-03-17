package ichop.service.post;

import ichop.domain.models.binding.post.PostCreateBindingModel;
import ichop.domain.models.service.post.PostServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

import java.util.List;

public interface PostServices {


    PostServiceModel createPost(UserServiceModel user, UserServiceModel creator , PostCreateBindingModel postCreateBindingModel);

    List<PostServiceModel> findPostsByUser(UserServiceModel user);

    PostServiceModel findPostById(String postId);

    void deletePost(PostServiceModel postServiceModel);
}
