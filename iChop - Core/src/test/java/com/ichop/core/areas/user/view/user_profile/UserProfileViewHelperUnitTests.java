package com.ichop.core.areas.user.view.user_profile;

import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import com.ichop.core.areas.player.services.PlayerLinkJmsServices;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.services.CommentReactionServices;
import com.ichop.core.areas.reaction.services.ThreadReactionServices;
import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_profile.UserInformationProfileViewModel;
import com.ichop.core.areas.user.domain.models.view.user_profile.UserProfileViewModel;
import com.ichop.core.areas.user.helpers.view.user_profile.PostsUserProfileViewHelper;
import com.ichop.core.areas.user.helpers.view.user_profile.UserInformationProfileViewHelper;
import com.ichop.core.areas.user.helpers.view.user_profile.UserProfileViewHelper;
import com.ichop.core.areas.user.services.UserFollowServices;
import com.ichop.core.areas.user.services.UserServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileViewHelperUnitTests {

    @Mock
    private UserServices userServices;

    @Mock
    private UserRoleServices userRoleServices;

    @Mock
    private CommentServices commentServices;

    @Mock
    private PlayerLinkJmsServices playerLinkJmsServices;

    @Mock
    private ThreadReactionServices threadReactionServices;

    @Mock
    private CommentReactionServices commentReactionServices;

    @Mock
    private PostsUserProfileViewHelper postsUserProfileViewHelper;

    @Mock
    private UserFollowServices userFollowServices;

    @Mock
    private UserInformationProfileViewHelper userInformationProfileViewHelper;

    @Mock
    private ModelMapper modelMapper;


    @Spy
    @InjectMocks
    private UserProfileViewHelper userProfileViewHelper;

    @Test
    public void create_withValidData_shouldInvokeMethods(){
        String username = "username";

        UserServiceModel user = mock(UserServiceModel.class);
        UserRoleServiceModel role = mock(UserRoleServiceModel.class);
        PlayerDataBySiteUserJMSReceiveModel playerData = mock(PlayerDataBySiteUserJMSReceiveModel.class);
        UserInformationProfileViewModel userInformationProfileViewModel = mock(UserInformationProfileViewModel.class);
        UserProfileViewModel result = mock(UserProfileViewModel.class);

        when(this.userServices.findUserByUsername(username)).thenReturn(user);
        when(user.getUsername()).thenReturn(username);
        when(this.userRoleServices.findHighestOfUser(user)).thenReturn(role);
        when(this.commentServices.getTotalOfUser(user)).thenReturn(4);
        when(role.getAuthority()).thenReturn("MODERATOR");
        when(this.playerLinkJmsServices.getPlayerDataBySiteUser(user.getUsername())).thenReturn(playerData);
        when(playerData.getPlayerUUID()).thenReturn("playerUUID");
        when(playerData.getPlayerName()).thenReturn("playerName");
        when(this.threadReactionServices.findTotalReactionsByUserAndType(user, ReactionType.LIKE)).thenReturn(1);
        when(this.threadReactionServices.findTotalReactionsByUserAndType(user, ReactionType.DISLIKE)).thenReturn(1);
        when(this.commentReactionServices.findTotalReactionsByUserAndType(user, ReactionType.LIKE)).thenReturn(1);
        when(this.commentReactionServices.findTotalReactionsByUserAndType(user, ReactionType.DISLIKE)).thenReturn(1);
        when(this.postsUserProfileViewHelper.create(user.getUsername())).thenReturn(new LinkedList<>());
        when(this.userFollowServices.getFollowings(user)).thenReturn(new LinkedList<>());
        when(this.userFollowServices.getFollowers(user)).thenReturn(new LinkedList<>());
        when(this.userInformationProfileViewHelper.create(user)).thenReturn(userInformationProfileViewModel);
        when(this.modelMapper.map(user, UserProfileViewModel.class)).thenReturn(result);

        this.userProfileViewHelper.create(username);


        verify(result,times(1)).setRole(role.getAuthority());
        verify(result,times(1)).setTotalMessages(4);
        verify(result,times(1)).setMinecraftAccountUUID(playerData.getPlayerUUID());
        verify(result,times(1)).setMinecraftAccountName(playerData.getPlayerName());
        verify(result,times(1)).setTotalLikes(2);
        verify(result,times(1)).setTotalDislikes(2);
        verify(this.userServices,times(1)).findUserByUsername(username);
        verify(this.userRoleServices,times(1)).findHighestOfUser(user);
        verify(this.playerLinkJmsServices,times(1)).getPlayerDataBySiteUser(user.getUsername());
        verify(this.threadReactionServices,times(1)).findTotalReactionsByUserAndType(user,ReactionType.LIKE);
        verify(this.threadReactionServices,times(1)).findTotalReactionsByUserAndType(user,ReactionType.DISLIKE);
        verify(this.commentReactionServices,times(1)).findTotalReactionsByUserAndType(user,ReactionType.LIKE);
        verify(this.commentReactionServices,times(1)).findTotalReactionsByUserAndType(user,ReactionType.DISLIKE);
        verify(this.postsUserProfileViewHelper,times(1)).create(user.getUsername());
        verify(this.userFollowServices,times(1)).getFollowings(user);
        verify(this.userFollowServices,times(1)).getFollowers(user);
        verify(this.userInformationProfileViewHelper,times(1)).create(user);
        verify(this.modelMapper,times(1)).map(user,UserProfileViewModel.class);
    }


    @Test
    public void create_withNullPlayerData_shouldInvokeMethodsAndSetPlayerDataToNull(){
        String username = "username";

        UserServiceModel user = mock(UserServiceModel.class);
        UserRoleServiceModel role = mock(UserRoleServiceModel.class);
        UserInformationProfileViewModel userInformationProfileViewModel = mock(UserInformationProfileViewModel.class);
        UserProfileViewModel result = mock(UserProfileViewModel.class);

        when(this.userServices.findUserByUsername(username)).thenReturn(user);
        when(user.getUsername()).thenReturn(username);
        when(this.userRoleServices.findHighestOfUser(user)).thenReturn(role);
        when(this.commentServices.getTotalOfUser(user)).thenReturn(4);
        when(role.getAuthority()).thenReturn("MODERATOR");
        when(this.threadReactionServices.findTotalReactionsByUserAndType(user, ReactionType.LIKE)).thenReturn(1);
        when(this.threadReactionServices.findTotalReactionsByUserAndType(user, ReactionType.DISLIKE)).thenReturn(1);
        when(this.commentReactionServices.findTotalReactionsByUserAndType(user, ReactionType.LIKE)).thenReturn(1);
        when(this.commentReactionServices.findTotalReactionsByUserAndType(user, ReactionType.DISLIKE)).thenReturn(1);
        when(this.postsUserProfileViewHelper.create(user.getUsername())).thenReturn(new LinkedList<>());
        when(this.userFollowServices.getFollowings(user)).thenReturn(new LinkedList<>());
        when(this.userFollowServices.getFollowers(user)).thenReturn(new LinkedList<>());
        when(this.userInformationProfileViewHelper.create(user)).thenReturn(userInformationProfileViewModel);
        when(this.modelMapper.map(user, UserProfileViewModel.class)).thenReturn(result);

        this.userProfileViewHelper.create(username);


        verify(result,times(1)).setRole(role.getAuthority());
        verify(result,times(1)).setTotalMessages(4);
        verify(result,times(1)).setMinecraftAccountUUID(null);
        verify(result,times(1)).setMinecraftAccountName(null);
        verify(result,times(1)).setTotalLikes(2);
        verify(result,times(1)).setTotalDislikes(2);
        verify(this.userServices,times(1)).findUserByUsername(username);
        verify(this.userRoleServices,times(1)).findHighestOfUser(user);
        verify(this.playerLinkJmsServices,times(1)).getPlayerDataBySiteUser(user.getUsername());
        verify(this.threadReactionServices,times(1)).findTotalReactionsByUserAndType(user,ReactionType.LIKE);
        verify(this.threadReactionServices,times(1)).findTotalReactionsByUserAndType(user,ReactionType.DISLIKE);
        verify(this.commentReactionServices,times(1)).findTotalReactionsByUserAndType(user,ReactionType.LIKE);
        verify(this.commentReactionServices,times(1)).findTotalReactionsByUserAndType(user,ReactionType.DISLIKE);
        verify(this.postsUserProfileViewHelper,times(1)).create(user.getUsername());
        verify(this.userFollowServices,times(1)).getFollowings(user);
        verify(this.userFollowServices,times(1)).getFollowers(user);
        verify(this.userInformationProfileViewHelper,times(1)).create(user);
        verify(this.modelMapper,times(1)).map(user,UserProfileViewModel.class);
    }

}
