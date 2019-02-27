package ichop.service.thread;

import ichop.domain.entities.threads.Comment;
import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.thread.CommentCreateBindingModel;
import ichop.domain.models.service.CommentServiceModel;
import ichop.domain.models.service.ThreadServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.service.thread.crud.CommentCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServicesImp implements CommentServices {

    private final CommentCrudServices commentCrudServices;
    private final ModelMapper modelMapper;

    public CommentServicesImp(CommentCrudServices commentCrudServices, ModelMapper modelMapper) {
        this.commentCrudServices = commentCrudServices;
        this.modelMapper = modelMapper;
    }


    @Override
    public CommentServiceModel addComment(CommentCreateBindingModel commentCreateBindingModel, UserServiceModel userServiceModel, ThreadServiceModel threadServiceModel) {

        Thread thread = this.modelMapper.map(threadServiceModel,Thread.class);
        User user = this.modelMapper.map(userServiceModel,User.class);
        Comment comment = this.modelMapper.map(commentCreateBindingModel,Comment.class);

        comment.setCreatedOn(LocalDateTime.now());
        comment.setCreator(user);
        comment.setThread(thread);

        CommentServiceModel commentServiceModel = this.commentCrudServices.save(this.modelMapper.map(comment,CommentServiceModel.class));

        return commentServiceModel;
    }

}
