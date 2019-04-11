package com.ichop.core.areas.user.view.user_follow_all;

import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_follow_all.UserFollowAllViewModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.areas.user.helpers.view.user_follow_all.UserFollowAllViewHelper;
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
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserFollowAllViewHelperUnitTests {

    @Mock
    private UserServices userServices;

    @Mock
    private UserFollowServices userFollowServices;

    @Mock
    private UserRoleServices userRoleServices;

    @Mock
    private ModelMapper modelMapper;

    @Spy
    @InjectMocks
    private UserFollowAllViewHelper userFollowAllViewHelper;


    @Test(expected = UserNotFoundException.class)
    public void createFollowers_withNotExistingUser_shouldThrowException() {
        String username = "username";
        when(this.userServices.findUserByUsername(username)).thenReturn(null);

        this.userFollowAllViewHelper.createFollowers(username);

    }

    @Test
    public void createFollowers_withValidData_shouldInvokeMethods(){
        String username = "username";
        UserServiceModel user = mock(UserServiceModel.class);
        List<UserServiceModel> followers = new LinkedList<>();

        when(this.userServices.findUserByUsername(username)).thenReturn(user);
        when(this.userFollowServices.getFollowers(user)).thenReturn(followers);

        this.userFollowAllViewHelper.createFollowers(username);

        verify(this.userServices,times(1)).findUserByUsername(username);
        verify(this.userFollowServices,times(1)).getFollowers(user);
    }


    @Test(expected = UserNotFoundException.class)
    public void createFollowings_withNotExistingUser_shouldThrowException() {
        String username = "username";
        when(this.userServices.findUserByUsername(username)).thenReturn(null);

        this.userFollowAllViewHelper.createFollowings(username);

    }

    @Test
    public void createFollowings_withValidData_shouldInvokeMethods(){
        String username = "username";
        UserServiceModel user = mock(UserServiceModel.class);
        List<UserServiceModel> followings = new LinkedList<>();

        when(this.userServices.findUserByUsername(username)).thenReturn(user);
        when(this.userFollowServices.getFollowings(user)).thenReturn(followings);

        this.userFollowAllViewHelper.createFollowings(username);

        verify(this.userServices,times(1)).findUserByUsername(username);
        verify(this.userFollowServices,times(1)).getFollowings(user);
    }

    @Test
    public void create_withValidData_shouldInvokeMethods(){
        String username = "username";
        UserServiceModel user = mock(UserServiceModel.class);
        List<UserServiceModel> followings = new LinkedList<>();
        UserServiceModel following = mock(UserServiceModel.class);
        followings.add(following);
        UserRoleServiceModel userRole = mock(UserRoleServiceModel.class);
        UserFollowAllViewModel userFollowAllViewModel = mock(UserFollowAllViewModel.class);

        when(this.userRoleServices.findHighestOfUser(following)).thenReturn(userRole);
        when(this.userServices.findUserByUsername(username)).thenReturn(user);
        when(this.userFollowServices.getFollowings(user)).thenReturn(followings);
        when(this.modelMapper.map(following,UserFollowAllViewModel.class)).thenReturn(userFollowAllViewModel);
        when(userRole.getAuthority()).thenReturn("MODERATOR");

        this.userFollowAllViewHelper.createFollowings(username);

        verify(userFollowAllViewModel,times(1)).setRole(userRole.getAuthority());
        verify(this.userServices,times(1)).findUserByUsername(username);
        verify(this.userFollowServices,times(1)).getFollowings(user);
        verify(this.modelMapper,times(1)).map(following, UserFollowAllViewModel.class);
    }

}
