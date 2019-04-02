package com.ichop.core.areas.post.services;

import com.ichop.core.areas.post.domain.entities.Post;
import com.ichop.core.areas.post.domain.models.binding.PostCreateBindingModel;
import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.post.exceptions.PostNotFoundException;
import com.ichop.core.areas.post.repositories.PostRepository;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.base.BaseService;
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
    public PostServiceModel create(UserServiceModel user, UserServiceModel creator , PostCreateBindingModel postCreateBindingModel) {

        if(user == null || creator == null){
            throw new UserNotFoundException();
        }

        PostServiceModel post = super.modelMapper.map(postCreateBindingModel,PostServiceModel.class);

        post.setUser(user);
        post.setCreator(creator);
        post.setCreatedOn(LocalDateTime.now());

        PostServiceModel savedPost = super.save(post,PostServiceModel.class);

        return savedPost;
    }

    @Override
    public List<PostServiceModel> findByUser(UserServiceModel user) {
        User entityUser = super.modelMapper.map(user,User.class);
        return super.repository.findAllByUser(entityUser).stream().map(x-> super.modelMapper.map(x,PostServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostServiceModel findById(String postId) {
        return super.findById(postId,PostServiceModel.class);
    }

    @Override
    public void delete(PostServiceModel postServiceModel) {

        if(postServiceModel == null || !super.existsById(postServiceModel.getId())){
            throw new PostNotFoundException();
        }

        super.delete(postServiceModel);
    }
}
