package ichop.service.user.crud;

import ichop.domain.entities.users.post.Post;
import ichop.domain.models.service.user.PostServiceModel;
import ichop.repository.user.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PostServiceModel save(PostServiceModel postServiceModel) {
        Post post = this.modelMapper.map(postServiceModel,Post.class);
        this.postRepository.save(post);

        return this.modelMapper.map(post,PostServiceModel.class);
    }

    @Override
    public void delete(PostServiceModel postServiceModel) {
        Post post = this.modelMapper.map(postServiceModel,Post.class);
        this.postRepository.delete(post);
    }
}
