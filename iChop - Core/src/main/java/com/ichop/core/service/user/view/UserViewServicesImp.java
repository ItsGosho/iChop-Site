package com.ichop.core.service.user.view;

import com.ichop.core.domain.entities.log.UserLogType;
import com.ichop.core.domain.entities.reaction.ReactionType;
import com.ichop.core.domain.entities.users.UserRoles;
import com.ichop.core.domain.models.service.role.UserRoleServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.domain.models.view.user_all.UsersAllViewModel;
import com.ichop.core.domain.models.view.user_control.UserControlHomeViewModel;
import com.ichop.core.domain.models.view.user_control.UserControlRoleManagementRoleHistoryViewModel;
import com.ichop.core.domain.models.view.user_control.UserControlRoleManagementViewModel;
import com.ichop.core.domain.models.view.user_options.UserProfileOptionsInformationViewModel;
import com.ichop.core.domain.models.view.user_profile.PostsUserProfileViewModel;
import com.ichop.core.domain.models.view.user_profile.UserProfileViewModel;
import com.ichop.core.exceptions.user.UserNotFoundException;
import com.ichop.core.service.comment.CommentServices;
import com.ichop.core.service.log.UserLogServices;
import com.ichop.core.service.player.PlayerServices;
import com.ichop.core.service.post.PostServices;
import com.ichop.core.service.reaction.CommentReactionServices;
import com.ichop.core.service.reaction.ThreadReactionServices;
import com.ichop.core.service.user.UserServices;
import com.ichop.core.service.role.UserRoleServices;
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
    private final UserLogServices userLogServices;
    private final PostServices postServices;
    private final PlayerServices playerServices;
    private final ModelMapper modelMapper;

    @Autowired
    public UserViewServicesImp(UserServices userServices, UserRoleServices userRoleServices, CommentServices commentServices, ThreadReactionServices threadReactionServices, CommentReactionServices commentReactionServices, UserLogServices userLogServices, PostServices postServices, PlayerServices playerServices, ModelMapper modelMapper) {
        this.userServices = userServices;
        this.userRoleServices = userRoleServices;
        this.commentServices = commentServices;
        this.threadReactionServices = threadReactionServices;
        this.commentReactionServices = commentReactionServices;
        this.userLogServices = userLogServices;
        this.postServices = postServices;
        this.playerServices = playerServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserProfileViewModel getUserProfileViewModel(String username) {

        if (!this.userServices.isUserExistsByUsername(username)) {
            throw new UserNotFoundException();
        }

        UserServiceModel user = this.userServices.findUserByUsername(username);

        UserProfileViewModel result = this.modelMapper.map(user, UserProfileViewModel.class);
        result.setRole(this.userRoleServices.findHighestRoleOfUser(user).getAuthority());
        result.setTotalMessages(this.commentServices.getTotalCommentsOfUser(user));
        result.setMinecraftAccountName((String) this.playerServices.getPlayerDataBySiteUser(username).get("name"));

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
    public UserProfileOptionsInformationViewModel getUserProfileOptionsInformationViewModel(String username) {
        UserServiceModel user = this.userServices.findUserByUsername(username);

        UserProfileOptionsInformationViewModel result = this.modelMapper.map(user.getUserInformation(),UserProfileOptionsInformationViewModel.class);

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

        userControlRoleManagementViewModel.setRoleChangeHistory(this.userLogServices.findAllByUserAndLogType(user, UserLogType.ROLE_CHANGE).stream().map(x-> this.modelMapper.map(x, UserControlRoleManagementRoleHistoryViewModel.class)).collect(Collectors.toList()));

        if (nextRole != null) {
            userControlRoleManagementViewModel.setNextRole(nextRole.getAuthority());
        }

        if (previousRole != null) {
            userControlRoleManagementViewModel.setPreviousRole(previousRole.getAuthority());
        }

        return userControlRoleManagementViewModel;
    }
}
