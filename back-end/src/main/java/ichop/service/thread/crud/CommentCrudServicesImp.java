package ichop.service.thread.crud;

import ichop.domain.entities.users.User;
import ichop.domain.models.service.UserServiceModel;
import ichop.repository.thread.CommentRepository;
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

}
