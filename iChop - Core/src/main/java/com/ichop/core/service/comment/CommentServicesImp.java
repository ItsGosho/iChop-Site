package com.ichop.core.service.comment;

import com.ichop.core.domain.entities.comment.Comment;
import com.ichop.core.domain.entities.users.User;
import com.ichop.core.domain.models.binding.comment.CommentCreateBindingModel;
import com.ichop.core.domain.models.service.comment.CommentServiceModel;
import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.exceptions.thread.ThreadNotFoundException;
import com.ichop.core.exceptions.user.UserNotFoundException;
import com.ichop.core.repository.comment.CommentRepository;
import com.ichop.core.service.BaseService;
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
    public CommentServiceModel createComment(CommentCreateBindingModel commentCreateBindingModel, UserServiceModel user, ThreadServiceModel thread) {

        if(user == null){
            throw new UserNotFoundException();
        }

        if(thread == null){
            throw new ThreadNotFoundException();
        }

        CommentServiceModel comment = this.modelMapper.map(commentCreateBindingModel,CommentServiceModel.class);

        comment.setCreatedOn(LocalDateTime.now());
        comment.setCreator(user);
        comment.setThread(thread);

        CommentServiceModel result = super.save(comment,CommentServiceModel.class);

        return result;
    }

    @Override
    public int getTotalCommentsOfUser(UserServiceModel user) {
        User entityUser = this.modelMapper.map(user, User.class);

        return super.repository.getTotalCommentsOfUser(entityUser);
    }

    @Override
    public CommentServiceModel findCommentById(String commentId) {
        return super.findById(commentId,CommentServiceModel.class);
    }

    @Override
    public void deleteComment(String commentId) {
        super.deleteById(commentId);
    }

}
