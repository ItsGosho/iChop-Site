package ichop.service.post.crud;

import ichop.domain.entities.users.User;
import ichop.domain.entities.post.Post;
import ichop.domain.models.service.post.PostServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.repository.post.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostCrudServicesImp implements PostCrudServices {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostCrudServicesImp(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostServiceModel save(PostServiceModel post) {
        Post entityPost = this.modelMapper.map(post,Post.class);
        this.postRepository.save(entityPost);

        return this.modelMapper.map(entityPost,PostServiceModel.class);
    }

    @Override
    public PostServiceModel getById(String id) {
        Post entityPost = this.postRepository.findById(id).orElse(null);

        if(entityPost != null){
            return this.modelMapper.map(entityPost,PostServiceModel.class);
        }

        return null;
    }

    @Override
    public void delete(PostServiceModel post) {
        Post entitiyPost = this.modelMapper.map(post,Post.class);
        this.postRepository.delete(entitiyPost);
    }

    @Override
    public List<PostServiceModel> getUserPosts(UserServiceModel user) {
        User entityUser = this.modelMapper.map(user,User.class);
        return this.postRepository.findAllByUser(entityUser)
                .stream()
                .map(x->this.modelMapper.map(x,PostServiceModel.class))
                .collect(Collectors.toList());
    }
}