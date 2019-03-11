package ichop.service.comment.crud;

import ichop.domain.entities.comment.Comment;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.thread.CommentNotFoundException;
import ichop.repository.comment.CommentRepository;
import ichop.service.reaction.crud.ReactionCrudServices;
import ichop.service.report.crud.ReportCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        this.commentRepository.delete(this.modelMapper.map(comment,Comment.class));
    }

}
