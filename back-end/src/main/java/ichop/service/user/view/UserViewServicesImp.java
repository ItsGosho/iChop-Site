package ichop.service.user.view;

import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.entities.users.UserRoles;
import ichop.domain.models.service.role.UserRoleServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.domain.models.view.user_all.UsersAllViewModel;
import ichop.domain.models.view.user_control.UserControlHomeViewModel;
import ichop.domain.models.view.user_control.UserControlRoleManagementViewModel;
import ichop.domain.models.view.user_profile.PostsUserProfileViewModel;
import ichop.domain.models.view.user_profile.UserProfileViewModel;
import ichop.exceptions.user.UserNotFoundException;
import ichop.service.comment.CommentServices;
import ichop.service.post.PostServices;
import ichop.service.reaction.CommentReactionServices;
import ichop.service.reaction.ThreadReactionServices;
import ichop.service.role.UserRoleServices;
import ichop.service.user.UserServices;
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

    private final UserServices userServices;
    private final UserRoleServices userRoleServices;
    private final CommentServices commentServices;
    private final ThreadReactionServices threadReactionServices;
    private final CommentReactionServices commentReactionServices;
    private final PostServices postServices;
    private final ModelMapper modelMapper;

    @Autowired
    public UserViewServicesImp(UserServices userServices, UserRoleServices userRoleServices, CommentServices commentServices, ThreadReactionServices threadReactionServices, CommentReactionServices commentReactionServices, PostServices postServices, ModelMapper modelMapper) {
        this.userServices = userServices;
        this.userRoleServices = userRoleServices;
        this.commentServices = commentServices;
        this.threadReactionServices = threadReactionServices;
        this.commentReactionServices = commentReactionServices;
        this.postServices = postServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserProfileViewModel getByUsername(String username) {

        if (!this.userServices.isUserExistsByUsername(username)) {
            throw new UserNotFoundException();
        }

        UserServiceModel user = this.userServices.findUserByUsername(username);

        UserProfileViewModel result = this.modelMapper.map(user, UserProfileViewModel.class);
        result.setRole(this.userRoleServices.findHighestRoleOfUser(user).getAuthority());
        result.setTotalMessages(this.commentServices.getTotalCommentsOfUser(user));

        int totalLikes = this.threadReactionServices.findTotalThreadReactionsByUserAndType(user, ReactionType.LIKE) + this.commentReactionServices.findTotalCommentReactionsByUserAndType(user,ReactionType.LIKE);
        int totalDislikes = this.threadReactionServices.findTotalThreadReactionsByUserAndType(user, ReactionType.DISLIKE) + this.commentReactionServices.findTotalCommentReactionsByUserAndType(user,ReactionType.DISLIKE);

        result.setTotalLikes(totalLikes);
        result.setTotalDislikes(totalDislikes);

        List<PostsUserProfileViewModel> postsUserProfileViewModels = this.postServices
                .findPostsByUser(user)
                .stream()
                .map(x -> this.modelMapper.map(x, PostsUserProfileViewModel.class))
                .sorted((x1, x2) -> x2.getCreatedOn().compareTo(x1.getCreatedOn()))
                .collect(Collectors.toList());

        result.setPosts(postsUserProfileViewModels);

        result.setTotalFollowers(this.userServices.findUserTotalFollowers(user));
        result.setTotalFollowing(this.userServices.findUserTotalFollowings(user));

        result.setFollowers(this.userServices.getFollowers(user));


        //TODO:

        return result;
    }

    @Override
    public Page<UsersAllViewModel> listAllByPage(Pageable pageable) {
        return this.userServices.findAll(pageable).map(x -> this.modelMapper.map(x, UsersAllViewModel.class));
    }

    @Override
    public Page<UsersAllViewModel> findUsersByUsernameContains(String containingWord, Pageable pageable) {
        return this.userServices.findUsersByUsernameContains(containingWord, pageable).map(x -> this.modelMapper.map(x, UsersAllViewModel.class));
    }

    @Override
    public Page<UsersAllViewModel> findUsersByRole(String role, Pageable pageable) {

        Page<UserServiceModel> users = this.userServices.findUsersWhomHasRole(role, pageable);

        //To filter only these ,whom highest role is the provided
        List<UsersAllViewModel> usersAllViewModels = users.stream()
                .filter(x -> this.userRoleServices.findHighestRoleOfUser(x).getAuthority().toUpperCase().equals(UserRoles.valueOf(role).name().toUpperCase()))
                .map(x -> this.modelMapper.map(x, UsersAllViewModel.class))
                .collect(Collectors.toList());

        Page<UsersAllViewModel> result = new PageImpl<>(usersAllViewModels);

        return result;
    }

    @Override
    public UserControlHomeViewModel getUserControlHomeViewModel(String userUsername) {

        UserServiceModel user = this.userServices.findUserByUsername(userUsername);
        UserRoleServiceModel currentUserRole = this.userRoleServices.findHighestRoleOfUser(user);

        UserControlHomeViewModel userControlHomeViewModel = this.modelMapper.map(user, UserControlHomeViewModel.class);
        userControlHomeViewModel.setRole(currentUserRole.getAuthority());

        return userControlHomeViewModel;
    }

    @Override
    public UserControlRoleManagementViewModel getUserControlRoleManagementViewModel(String userUsername) {

        UserServiceModel user = this.userServices.findUserByUsername(userUsername);
        UserRoleServiceModel currentUserRole = this.userRoleServices.findHighestRoleOfUser(user);
        UserRoleServiceModel nextRole = this.userRoleServices.getUserNextRole(currentUserRole);
        UserRoleServiceModel previousRole = this.userRoleServices.getUserPreviousRole(currentUserRole);

        UserControlRoleManagementViewModel userControlRoleManagementViewModel = this.modelMapper.map(user, UserControlRoleManagementViewModel.class);
        userControlRoleManagementViewModel.setRole(currentUserRole.getAuthority());

        if (nextRole != null) {
            userControlRoleManagementViewModel.setNextRole(nextRole.getAuthority());
        }

        if (previousRole != null) {
            userControlRoleManagementViewModel.setPreviousRole(previousRole.getAuthority());
        }

        return userControlRoleManagementViewModel;
    }
}
