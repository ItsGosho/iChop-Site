package ichop.service.user.view;

import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserRoles;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.domain.models.view.post.PostsProfileViewModel;
import ichop.domain.models.view.user.UserProfileViewModel;
import ichop.domain.models.view.user.UsersAllViewModel;
import ichop.exceptions.user.UserNotFoundException;
import ichop.service.role.UserRoleServices;
import ichop.service.comment.crud.CommentCrudServices;
import ichop.service.reaction.crud.ReactionCrudServices;
import ichop.service.post.crud.PostCrudServices;
import ichop.service.user.crud.UserCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserViewServicesImp implements UserViewServices {

    private final UserCrudServices userCrudServices;
    private final CommentCrudServices commentCrudServices;
    private final ReactionCrudServices reactionCrudServices;
    private final PostCrudServices postCrudServices;
    private final UserRoleServices userRoleServices;
    private final ModelMapper modelMapper;

    @Autowired
    public UserViewServicesImp(UserCrudServices userCrudServices, CommentCrudServices commentCrudServices, ReactionCrudServices reactionCrudServices, PostCrudServices postCrudServices, UserRoleServices userRoleServices, ModelMapper modelMapper) {
        this.userCrudServices = userCrudServices;
        this.commentCrudServices = commentCrudServices;
        this.reactionCrudServices = reactionCrudServices;
        this.postCrudServices = postCrudServices;
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

        List<PostsProfileViewModel> postsProfileViewModels = this.postCrudServices
                .getUserPosts(user)
                .stream()
                .map(x -> this.modelMapper.map(x, PostsProfileViewModel.class))
                .sorted((x1, x2) -> x2.getCreatedOn().compareTo(x1.getCreatedOn()))
                .collect(Collectors.toList());

        result.setPosts(postsProfileViewModels);

        result.setTotalFollowers(this.userCrudServices.getUserTotalFollowers(user));
        result.setTotalFollowing(this.userCrudServices.getUserTotalFollowings(user));

        result.setFollowers(this.userCrudServices.getFollowers(user));


        //TODO:

        return result;
    }

    @Override
    public Page<UsersAllViewModel> listAllByPage(Pageable pageable) {
        return this.userCrudServices.findAll(pageable).map(x -> this.modelMapper.map(x, UsersAllViewModel.class));
    }

    @Override
    public Page<UsersAllViewModel> findUsersByUsernameContains(String containingWord, Pageable pageable) {
        return this.userCrudServices.findUsersByUsernameContains(containingWord, pageable).map(x -> this.modelMapper.map(x, UsersAllViewModel.class));
    }

    @Override
    public Page<UsersAllViewModel> findUsersByRole(String role, Pageable pageable) {

        Page<UserServiceModel> users = this.userCrudServices.findUsersWhomHasRole(role,pageable);

        //To filter only these ,whom highest role is the provided
        List<UsersAllViewModel> usersAllViewModels = users.stream()
                .filter(x-> this.userRoleServices.getRole(x).getAuthority().toUpperCase().equals(UserRoles.valueOf(role).name().toUpperCase()))
                .map(x-> this.modelMapper.map(x,UsersAllViewModel.class))
                .collect(Collectors.toList());

        Page<UsersAllViewModel> result = new PageImpl<>(usersAllViewModels);

        return result;
    }
}
