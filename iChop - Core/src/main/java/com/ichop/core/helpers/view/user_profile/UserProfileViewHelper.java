package com.ichop.core.helpers.view.user_profile;

import com.ichop.core.domain.entities.reaction.ReactionType;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.domain.models.view.user_profile.PostsUserProfileViewModel;
import com.ichop.core.domain.models.view.user_profile.UserProfileViewModel;
import com.ichop.core.helpers.view.BaseViewCreator;
import com.ichop.core.service.comment.CommentServices;
import com.ichop.core.service.player.link.PlayerLinkServices;
import com.ichop.core.service.reaction.CommentReactionServices;
import com.ichop.core.service.reaction.ThreadReactionServices;
import com.ichop.core.service.role.UserRoleServices;
import com.ichop.core.service.user.UserServices;
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
    private final PlayerLinkServices playerLinkServices;
    private final ThreadReactionServices threadReactionServices;
    private final CommentReactionServices commentReactionServices;
    private final PostsUserProfileViewHelper postsUserProfileViewHelper;

    @Autowired
    public UserProfileViewHelper(ModelMapper modelMapper, UserServices userServices, UserRoleServices userRoleServices, CommentServices commentServices, PlayerLinkServices playerLinkServices, ThreadReactionServices threadReactionServices, CommentReactionServices commentReactionServices, PostsUserProfileViewHelper postsUserProfileViewHelper) {
        super(modelMapper);
        this.userServices = userServices;
        this.userRoleServices = userRoleServices;
        this.commentServices = commentServices;
        this.playerLinkServices = playerLinkServices;
        this.threadReactionServices = threadReactionServices;
        this.commentReactionServices = commentReactionServices;
        this.postsUserProfileViewHelper = postsUserProfileViewHelper;
    }

    public UserProfileViewModel create(String username){
        UserServiceModel user = this.userServices.findUserByUsername(username);

        String role = this.userRoleServices.findHighestRoleOfUser(user).getAuthority();
        int totalMessages = this.commentServices.getTotalCommentsOfUser(user);
        String minecraftAccountName = this.playerLinkServices.getPlayerDataBySiteUser(username) != null ? this.playerLinkServices.getPlayerDataBySiteUser(username).getPlayerName() : null;
        int totalLikes = this.threadReactionServices.findTotalThreadReactionsByUserAndType(user, ReactionType.LIKE) + this.commentReactionServices.findTotalCommentReactionsByUserAndType(user,ReactionType.LIKE);
        int totalDislikes = this.threadReactionServices.findTotalThreadReactionsByUserAndType(user, ReactionType.DISLIKE) + this.commentReactionServices.findTotalCommentReactionsByUserAndType(user,ReactionType.DISLIKE);
        List<PostsUserProfileViewModel> posts = this.postsUserProfileViewHelper.create(user.getUsername());
        List<UserProfileViewModel> followers =this.userServices.getFollowers(user).stream().map(x-> super.modelMapper.map(x,UserProfileViewModel.class)).collect(Collectors.toList()) ;

        UserProfileViewModel result = this.modelMapper.map(user, UserProfileViewModel.class);
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
