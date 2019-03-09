package ichop.service.user;

import ichop.domain.entities.users.User;
import ichop.domain.entities.users.post.Post;
import ichop.domain.models.binding.user.PostCreateBindingModel;
import ichop.domain.models.service.user.PostServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.user.UserNotFoundException;
import ichop.service.user.crud.PostCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostServicesImp implements PostServices {

    private final PostCrudServices postCrudServices;
    private final ModelMapper modelMapper;

    @Autowired
    public PostServicesImp(PostCrudServices postCrudServices, ModelMapper modelMapper) {
        this.postCrudServices = postCrudServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostServiceModel create(UserServiceModel userServiceModel,UserServiceModel creatorServiceModel , PostCreateBindingModel postCreateBindingModel) {

        if(userServiceModel == null || creatorServiceModel == null){
            throw new UserNotFoundException();
        }

        User user = this.modelMapper.map(userServiceModel,User.class);
        User creator = this.modelMapper.map(creatorServiceModel,User.class);
        Post post = this.modelMapper.map(postCreateBindingModel,Post.class);

        post.setUser(user);
        post.setCreator(creator);
        post.setCreatedOn(LocalDateTime.now());

        PostServiceModel result = this.postCrudServices.save(this.modelMapper.map(post,PostServiceModel.class));

        return result;
    }
}
