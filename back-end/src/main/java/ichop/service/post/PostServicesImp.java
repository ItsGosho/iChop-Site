package ichop.service.post;

import ichop.domain.models.binding.post.PostCreateBindingModel;
import ichop.domain.models.service.post.PostServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.user.UserNotFoundException;
import ichop.service.post.crud.PostCrudServices;
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
    public PostServiceModel create(UserServiceModel user,UserServiceModel creator , PostCreateBindingModel postCreateBindingModel) {

        if(user == null || creator == null){
            throw new UserNotFoundException();
        }

        PostServiceModel post = this.modelMapper.map(postCreateBindingModel,PostServiceModel.class);

        post.setUser(user);
        post.setCreator(creator);
        post.setCreatedOn(LocalDateTime.now());

        PostServiceModel savedPost = this.postCrudServices.save(post);

        return savedPost;
    }
}
