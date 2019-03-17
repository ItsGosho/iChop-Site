package ichop.service.comment;

import ichop.domain.entities.comment.Comment;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.comment.CommentCreateBindingModel;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.thread.ThreadNotFoundException;
import ichop.exceptions.user.UserNotFoundException;
import ichop.repository.comment.CommentRepository;
import ichop.service.BaseService;
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
