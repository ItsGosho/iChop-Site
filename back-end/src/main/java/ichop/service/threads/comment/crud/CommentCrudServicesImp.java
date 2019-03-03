package ichop.service.threads.comment.crud;

import ichop.domain.entities.threads.Comment;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.repository.threads.comment.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentCrudServicesImp implements CommentCrudServices {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentCrudServicesImp(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public int getTotalCommentsOfUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);

        return this.commentRepository.getTotalCommentsOfUser(user);
    }

    @Override
    public CommentServiceModel save(CommentServiceModel commentServiceModel) {
        Comment comment = this.modelMapper.map(commentServiceModel,Comment.class);
        this.commentRepository.save(comment);

        return this.modelMapper.map(comment,CommentServiceModel.class);
    }

    @Override
    public CommentServiceModel getById(String id) {
        Comment comment = this.commentRepository.findById(id).orElse(null);

        if(comment != null){
            return this.modelMapper.map(comment,CommentServiceModel.class);
        }

        return null;
    }

    @Override
    public void delete(CommentServiceModel commentServiceModel) {
        Comment comment = this.modelMapper.map(commentServiceModel,Comment.class);
        this.commentRepository.delete(comment);
    }

}
