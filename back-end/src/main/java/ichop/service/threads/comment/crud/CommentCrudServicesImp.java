package ichop.service.threads.comment.crud;

import ichop.domain.entities.threads.Comment;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.thread.CommentNotFoundException;
import ichop.repository.threads.comment.CommentRepository;
import ichop.service.threads.reaction.crud.ReactionCrudServices;
import ichop.service.threads.report.crud.ReportCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentCrudServicesImp implements CommentCrudServices {

    private final CommentRepository commentRepository;
    private final ReactionCrudServices reactionCrudServices;
    private final ReportCrudServices reportCrudServices;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentCrudServicesImp(CommentRepository commentRepository, ReactionCrudServices reactionCrudServices, ReportCrudServices reportCrudServices, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.reactionCrudServices = reactionCrudServices;
        this.reportCrudServices = reportCrudServices;
        this.modelMapper = modelMapper;
    }


    @Override
    public int getTotalCommentsOfUser(UserServiceModel user) {
        User entityUser = this.modelMapper.map(user, User.class);

        return this.commentRepository.getTotalCommentsOfUser(entityUser);
    }

    @Override
    public CommentServiceModel save(CommentServiceModel comment) {
        Comment entityComment = this.modelMapper.map(comment, Comment.class);
        this.commentRepository.save(entityComment);

        return this.modelMapper.map(entityComment, CommentServiceModel.class);
    }

    @Override
    public CommentServiceModel getById(String id) {
        Comment entityComment = this.commentRepository.findById(id).orElse(null);

        if (entityComment != null) {
            return this.modelMapper.map(entityComment, CommentServiceModel.class);
        }

        return null;
    }

    @Override
    public void delete(CommentServiceModel comment) {
        Comment entityComment = this.modelMapper.map(comment, Comment.class);
        this.commentRepository.delete(entityComment);
    }

    @Override
    public void delete(String id) {
        CommentServiceModel comment = this.getById(id);

        if (comment == null) {
              throw new CommentNotFoundException();
        }

        this.commentRepository.delete(this.modelMapper.map(comment,Comment.class));
    }

}
