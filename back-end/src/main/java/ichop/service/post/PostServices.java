package ichop.service.post;

import ichop.domain.models.binding.post.PostCreateBindingModel;
import ichop.domain.models.service.post.PostServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface PostServices {


    PostServiceModel create(UserServiceModel user,UserServiceModel creator , PostCreateBindingModel postCreateBindingModel);

}
