package com.ichop.core.areas.user.helpers.view.user_profile;

import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import com.ichop.core.areas.player.services.PlayerLinkJmsServices;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.services.CommentReactionServices;
import com.ichop.core.areas.reaction.services.ThreadReactionServices;
import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_profile.PostsUserProfileViewModel;
import com.ichop.core.areas.user.domain.models.view.user_profile.UserInformationProfileViewModel;
import com.ichop.core.areas.user.domain.models.view.user_profile.UserProfileViewModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.areas.user.services.UserFollowServices;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserProfileViewHelper extends BaseViewCreator {

    private final UserServices userServices;
    private final UserRoleServices userRoleServices;
    private final CommentServices commentServices;
    private final PlayerLinkJmsServices playerLinkJmsServices;
    private final ThreadReactionServices threadReactionServices;
    private final CommentReactionServices commentReactionServices;
    private final PostsUserProfileViewHelper postsUserProfileViewHelper;
    private final UserFollowServices userFollowServices;
    private final UserInformationProfileViewHelper userInformationProfileViewHelper;

    @Autowired
    protected UserProfileViewHelper(ModelMapper modelMapper, UserServices userServices, UserRoleServices userRoleServices, CommentServices commentServices, PlayerLinkJmsServices playerLinkJmsServices, ThreadReactionServices threadReactionServices, CommentReactionServices commentReactionServices, PostsUserProfileViewHelper postsUserProfileViewHelper, UserFollowServices userFollowServices, UserInformationProfileViewHelper userInformationProfileViewHelper) {
        super(modelMapper);
        this.userServices = userServices;
        this.userRoleServices = userRoleServices;
        this.commentServices = commentServices;
        this.playerLinkJmsServices = playerLinkJmsServices;
        this.threadReactionServices = threadReactionServices;
        this.commentReactionServices = commentReactionServices;
        this.postsUserProfileViewHelper = postsUserProfileViewHelper;
        this.userFollowServices = userFollowServices;
        this.userInformationProfileViewHelper = userInformationProfileViewHelper;
    }


    public UserProfileViewModel create(String username) {
        UserServiceModel user = this.userServices.findUserByUsername(username);

        if(user == null){
            throw new UserNotFoundException();
        }

        String role = this.userRoleServices.findHighestOfUser(user).getAuthority();
        int totalMessages = this.commentServices.getTotalOfUser(user);
        PlayerDataBySiteUserJMSReceiveModel playerData = this.playerLinkJmsServices.getPlayerDataBySiteUser(user.getUsername());
        String minecraftAccountName = playerData != null ? playerData.getPlayerName() : null;
        String minecraftAccountUUID = playerData != null ? playerData.getPlayerUUID() : null;
        int totalLikes = this.threadReactionServices.findTotalReactionsByUserAndType(user, ReactionType.LIKE) + this.commentReactionServices.findTotalReactionsByUserAndType(user, ReactionType.LIKE);
        int totalDislikes = this.threadReactionServices.findTotalReactionsByUserAndType(user, ReactionType.DISLIKE) + this.commentReactionServices.findTotalReactionsByUserAndType(user, ReactionType.DISLIKE);
        List<PostsUserProfileViewModel> posts = this.postsUserProfileViewHelper.create(user.getUsername());
        List<UserProfileViewModel> followers = this.userFollowServices.getFollowers(user).stream().map(x -> super.modelMapper.map(x, UserProfileViewModel.class)).collect(Collectors.toList());
        List<UserProfileViewModel> followings = this.userFollowServices.getFollowings(user).stream().map(x -> super.modelMapper.map(x, UserProfileViewModel.class)).collect(Collectors.toList());
        UserInformationProfileViewModel userInformationProfileViewModel = this.userInformationProfileViewHelper.create(user);

        UserProfileViewModel result = this.modelMapper.map(user, UserProfileViewModel.class);
        result.setRole(role);
        result.setTotalMessages(totalMessages);
        result.setMinecraftAccountName(minecraftAccountName);
        result.setMinecraftAccountUUID(minecraftAccountUUID);
        result.setTotalLikes(totalLikes);
        result.setTotalDislikes(totalDislikes);
        result.setPosts(posts);
        result.setTotalFollowers(this.userFollowServices.findUserTotalFollowers(user));
        result.setTotalFollowing(this.userFollowServices.findUserTotalFollowings(user));
        result.setFollowers(followers);
        result.setFollowings(followings);
        result.setInformation(userInformationProfileViewModel);

        return result;
    }

}
