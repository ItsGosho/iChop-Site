package ichop.service.user.view;

import ichop.domain.entities.base.ReactionType;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.domain.models.view.user.UserProfileViewModel;
import ichop.exceptions.user.UserNotFoundException;
import ichop.service.role.UserRoleServices;
import ichop.service.threads.comment.crud.CommentCrudServices;
import ichop.service.threads.reaction.crud.ReactionCrudServices;
import ichop.service.user.crud.UserCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserViewServicesImp implements UserViewServices {

    private final UserCrudServices userCrudServices;
    private final CommentCrudServices commentCrudServices;
    private final ReactionCrudServices reactionCrudServices;
    private final UserRoleServices userRoleServices;
    private final ModelMapper modelMapper;

    @Autowired
    public UserViewServicesImp(UserCrudServices userCrudServices, CommentCrudServices commentCrudServices, ReactionCrudServices reactionCrudServices, UserRoleServices userRoleServices, ModelMapper modelMapper) {
        this.userCrudServices = userCrudServices;
        this.commentCrudServices = commentCrudServices;
        this.reactionCrudServices = reactionCrudServices;
        this.userRoleServices = userRoleServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserProfileViewModel getByUsername(String username) {

        if (!this.userCrudServices.existsByUsername(username)) {
            throw new UserNotFoundException();
        }

        UserServiceModel user = this.userCrudServices.getUserByUsername(username);

        UserProfileViewModel result = this.modelMapper.map(user, UserProfileViewModel.class);
        result.setRole(this.userRoleServices.getRole(user).getAuthority());
        result.setTotalMessages(this.commentCrudServices.getTotalCommentsOfUser(user));

        int totalLikes = this.reactionCrudServices.getUserTotalReactions(user, ReactionType.LIKE);
        int totalDislikes = this.reactionCrudServices.getUserTotalReactions(user, ReactionType.DISLIKE);

        result.setTotalLikes(totalLikes);
        result.setTotalDislikes(totalDislikes);

        //TODO:

        return result;
    }
}
