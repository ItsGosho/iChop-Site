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
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServicesImp extends BaseService<Post, PostRepository> implements PostServices {

    @Autowired
    public PostServicesImp(ModelMapper modelMapper, PostRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public PostServiceModel create(PostCreateBindingModel postCreateBindingModel) {

        if(postCreateBindingModel.getUser() == null || postCreateBindingModel.getCreator() == null){
            throw new UserNotFoundException();
        }

        PostServiceModel post = this.modelMapper.map(postCreateBindingModel,PostServiceModel.class);
        post.setCreatedOn(LocalDateTime.now());
        post.setReports(new LinkedList<>());

        PostServiceModel savedPost = this.save(post,PostServiceModel.class);

        return savedPost;
    }

    @Override
    public List<PostServiceModel> findByUser(UserServiceModel user) {

        if(user == null){
            throw new UserNotFoundException();
        }

        User entityUser = this.modelMapper.map(user,User.class);
        List<Post> posts = this.repository.findAllByUser(entityUser);

        return posts.stream().map(x-> this.modelMapper.map(x,PostServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public PostServiceModel findById(String postId) {
        return this.findById(postId,PostServiceModel.class);
    }

    @Override
    public void deleteByModel(PostServiceModel postServiceModel) {

        if(postServiceModel == null || !this.existsById(postServiceModel.getId())){
            throw new PostNotFoundException();
        }

        this.delete(postServiceModel);
    }
}
