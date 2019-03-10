package ichop.service.user.crud;

import ichop.domain.models.service.user.PostServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

import java.util.List;

public interface PostCrudServices {

    PostServiceModel save(PostServiceModel post);
    void delete(PostServiceModel post);

    List<PostServiceModel> getUserPosts(UserServiceModel user);

}
