package ichop.service.threads.comment;

import ichop.domain.entities.threads.Comment;
import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.thread.CommentCreateBindingModel;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.thread.CommentNotFoundException;
import ichop.exceptions.thread.ThreadNotFoundException;
import ichop.exceptions.user.UserNotFoundException;
import ichop.service.threads.comment.crud.CommentCrudServices;
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
    public CommentServiceModel addComment(CommentCreateBindingModel commentCreateBindingModel, UserServiceModel user, ThreadServiceModel thread) {

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

        CommentServiceModel result = this.commentCrudServices.save(comment);

        return result;
    }

}
