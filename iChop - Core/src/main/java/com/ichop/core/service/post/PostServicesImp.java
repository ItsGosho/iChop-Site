package com.ichop.core.service.post;

import com.ichop.core.domain.entities.users.User;
import com.ichop.core.domain.models.binding.post.PostCreateBindingModel;
import com.ichop.core.domain.models.service.post.PostServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.exceptions.user.UserNotFoundException;
import com.ichop.core.repository.post.PostRepository;
import com.ichop.core.service.BaseService;
import com.ichop.core.domain.entities.post.Post;
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
