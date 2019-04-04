package com.ichop.core.areas.comment.services;

import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.comment.domain.models.binding.CommentCreateBindingModel;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.repositories.CommentRepository;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.exceptions.CommentNotFoundException;
import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.base.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServicesImp extends BaseService<Comment, CommentRepository> implements CommentServices {


    @Autowired
    public CommentServicesImp(ModelMapper modelMapper, CommentRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public CommentServiceModel create(CommentCreateBindingModel commentCreateBindingModel, UserServiceModel user, ThreadServiceModel thread) {

        if (user == null) {
            throw new UserNotFoundException();
        }

        if (thread == null) {
            throw new ThreadNotFoundException();
        }

        CommentServiceModel comment = this.modelMapper.map(commentCreateBindingModel, CommentServiceModel.class);

        comment.setCreatedOn(LocalDateTime.now());
        comment.setCreator(user);
        comment.setThread(thread);

        CommentServiceModel result = super.save(comment, CommentServiceModel.class);

        return result;
    }

    @Override
    public int getTotalOfUser(UserServiceModel user) {

        if(user == null){
            throw new UserNotFoundException();
        }

        User entityUser = this.modelMapper.map(user, User.class);

        return super.repository.getTotalCommentsOfUser(entityUser);
    }

    @Override
    public CommentServiceModel findById(String commentId) {
        return super.findById(commentId, CommentServiceModel.class);
    }

    @Override
    public void delete(String commentId) {

        if (!this.repository.existsById(commentId)) {
            throw new CommentNotFoundException();
        }

        super.deleteById(commentId);
    }

    @Override
    public void delete(CommentServiceModel commentServiceModel) {

        if (commentServiceModel == null || !this.repository.existsById(commentServiceModel.getId())){
            throw new CommentNotFoundException();
        }

        super.delete(commentServiceModel);
    }

}
