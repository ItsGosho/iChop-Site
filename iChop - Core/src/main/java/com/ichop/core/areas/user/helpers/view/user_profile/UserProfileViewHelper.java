package com.ichop.core.areas.user.helpers.view.user_profile;

import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import com.ichop.core.areas.player.services.PlayerLinkServices;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.services.CommentReactionServices;
import com.ichop.core.areas.reaction.services.ThreadReactionServices;
import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_profile.PostsUserProfileViewModel;
import com.ichop.core.areas.user.domain.models.view.user_profile.UserProfileViewModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserProfileViewHelper extends BaseViewCreator {

    private final UserServices userServices;
    private final UserRoleServices userRoleServices;
    private final CommentServices commentServices;
    private final PlayerLinkServices playerLinkServices;
    private final ThreadReactionServices threadReactionServices;
    private final CommentReactionServices commentReactionServices;
    private final PostsUserProfileViewHelper postsUserProfileViewHelper;

    protected UserProfileViewHelper(ModelMapper modelMapper, UserServices userServices, UserRoleServices userRoleServices, CommentServices commentServices, PlayerLinkServices playerLinkServices, ThreadReactionServices threadReactionServices, CommentReactionServices commentReactionServices, PostsUserProfileViewHelper postsUserProfileViewHelper) {
        super(modelMapper);
        this.userServices = userServices;
        this.userRoleServices = userRoleServices;
        this.commentServices = commentServices;
        this.playerLinkServices = playerLinkServices;
        this.threadReactionServices = threadReactionServices;
        this.commentReactionServices = commentReactionServices;
        this.postsUserProfileViewHelper = postsUserProfileViewHelper;
    }


    public UserProfileViewModel create(String username) {
        UserServiceModel user = this.userServices.findUserByUsername(username);

        if(user == null){
            throw new UserNotFoundException();
        }

        String role = this.userRoleServices.findHighestOfUser(user).getAuthority();
        int totalMessages = this.commentServices.getTotalOfUser(user);
        PlayerDataBySiteUserJMSReceiveModel playerData = this.playerLinkServices.getPlayerDataBySiteUser(user.getUsername());
        String minecraftAccountName = playerData != null ? playerData.getPlayerName() : null;
        int totalLikes = this.threadReactionServices.findTotalThreadReactionsByUserAndType(user, ReactionType.LIKE) + this.commentReactionServices.findTotalCommentReactionsByUserAndType(user, ReactionType.LIKE);
        int totalDislikes = this.threadReactionServices.findTotalThreadReactionsByUserAndType(user, ReactionType.DISLIKE) + this.commentReactionServices.findTotalCommentReactionsByUserAndType(user, ReactionType.DISLIKE);
        List<PostsUserProfileViewModel> posts = this.postsUserProfileViewHelper.create(user.getUsername());
        List<UserProfileViewModel> followers = this.userServices.getFollowers(user).stream().map(x -> super.modelMapper.map(x, UserProfileViewModel.class)).collect(Collectors.toList());

        UserProfileViewModel result = super.modelMapper.map(user, UserProfileViewModel.class);
        result.setRole(role);
        result.setTotalMessages(totalMessages);
        result.setMinecraftAccountName(minecraftAccountName);
        result.setTotalLikes(totalLikes);
        result.setTotalDislikes(totalDislikes);
        result.setPosts(posts);
        result.setTotalFollowers(this.userServices.findUserTotalFollowers(user));
        result.setTotalFollowing(this.userServices.findUserTotalFollowings(user));
        result.setFollowers(followers);

        return result;
    }

}
