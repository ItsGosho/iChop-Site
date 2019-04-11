package com.ichop.core.areas.comment.services;

import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.comment.domain.models.binding.CommentCreateBindingModel;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.exceptions.CommentNotFoundException;
import com.ichop.core.areas.comment.repositories.CommentRepository;
import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.base.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Service
public class CommentServicesImp extends BaseService<Comment, CommentRepository> implements CommentServices {


    @Autowired
    public CommentServicesImp(ModelMapper modelMapper, CommentRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public CommentServiceModel create(CommentCreateBindingModel commentCreateBindingModel) {

        if (commentCreateBindingModel.getCreator() == null) {
            throw new UserNotFoundException();
        }

        if (commentCreateBindingModel.getThread() == null) {
            throw new ThreadNotFoundException();
        }

        System.out.println(LocalDateTime.now());
        CommentServiceModel comment = this.modelMapper.map(commentCreateBindingModel, CommentServiceModel.class);
        comment.setCreatedOn(LocalDateTime.now());
        comment.setReactions(new LinkedList<>());
        comment.setReports(new LinkedList<>());

        CommentServiceModel result = this.save(comment, CommentServiceModel.class);

        return result;
    }

    @Override
    public int getTotalOfUser(UserServiceModel user) {

        if (user == null) {
            throw new UserNotFoundException();
        }

        User entityUser = this.modelMapper.map(user, User.class);

        return this.repository.getTotalCommentsOfUser(entityUser);
    }

    @Override
    public CommentServiceModel findById(String commentId) {
        return this.findById(commentId, CommentServiceModel.class);
    }

    @Override
    public void delete(String commentId) {

        if (!this.repository.existsById(commentId)) {
            throw new CommentNotFoundException();
        }

        this.deleteById(commentId);
    }

    @Override
    public void deleteByModel(CommentServiceModel commentServiceModel) {

        if (commentServiceModel == null || !this.repository.existsById(commentServiceModel.getId())) {
            throw new CommentNotFoundException();
        }

        this.delete(commentServiceModel);
    }

}
