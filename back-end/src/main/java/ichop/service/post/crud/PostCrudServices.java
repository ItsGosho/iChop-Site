package ichop.service.post.crud;

import ichop.domain.models.service.post.PostServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

import java.util.List;

public interface PostCrudServices {

    PostServiceModel save(PostServiceModel post);
    PostServiceModel getById(String id);
    void delete(PostServiceModel post);

    List<PostServiceModel> getUserPosts(UserServiceModel user);

}
