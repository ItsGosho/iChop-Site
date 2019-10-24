package ichop.core.areas.comment.services;

import ichop.core.areas.comment.domain.entities.Comment;
import ichop.core.areas.comment.domain.models.binding.CommentCreateBindingModel;
import ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import ichop.core.areas.comment.exceptions.CommentNotFoundException;
import ichop.core.areas.comment.repositories.CommentRepository;
import ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import ichop.core.areas.user.domain.entities.User;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.areas.user.exceptions.UserNotFoundException;
import ichop.core.base.BaseService;
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
