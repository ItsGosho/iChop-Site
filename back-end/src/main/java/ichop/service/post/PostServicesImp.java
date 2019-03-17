package ichop.service.post;

import ichop.domain.entities.post.Post;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.post.PostCreateBindingModel;
import ichop.domain.models.service.post.PostServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.user.UserNotFoundException;
import ichop.repository.post.PostRepository;
import ichop.service.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServicesImp extends BaseService<Post, PostRepository> implements PostServices {

    @Autowired
    public PostServicesImp(ModelMapper modelMapper, PostRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public PostServiceModel createPost(UserServiceModel user, UserServiceModel creator , PostCreateBindingModel postCreateBindingModel) {

        if(user == null || creator == null){
            throw new UserNotFoundException();
        }

        PostServiceModel post = this.modelMapper.map(postCreateBindingModel,PostServiceModel.class);

        post.setUser(user);
        post.setCreator(creator);
        post.setCreatedOn(LocalDateTime.now());

        PostServiceModel savedPost = super.save(post,PostServiceModel.class);

        return savedPost;
    }

    @Override
    public List<PostServiceModel> findPostsByUser(UserServiceModel user) {
        User entityUser = super.modelMapper.map(user,User.class);
        return super.repository.findAllByUser(entityUser).stream().map(x-> super.modelMapper.map(x,PostServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostServiceModel findPostById(String postId) {
        return super.findById(postId,PostServiceModel.class);
    }

    @Override
    public void deletePost(PostServiceModel postServiceModel) {
        super.delete(postServiceModel);
    }
}
