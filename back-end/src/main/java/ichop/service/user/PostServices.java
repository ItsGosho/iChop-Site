package ichop.service.user;

import ichop.domain.models.binding.user.PostCreateBindingModel;
import ichop.domain.models.service.user.PostServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface PostServices {


    PostServiceModel create(UserServiceModel userServiceModel,UserServiceModel creatorServiceModel , PostCreateBindingModel postCreateBindingModel);

}
