package com.ichop.core.areas.user.services;

import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.entities.UserFollow;
import com.ichop.core.areas.user.domain.models.service.UserFollowServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserCannotFollowException;
import com.ichop.core.areas.user.exceptions.UserNotFollowingHimException;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.areas.user.repositories.UserFollowRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserFollowServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserFollowRepository userFollowRepository;

    @Spy
    @InjectMocks
    private UserFollowServicesImp userFollowServices;

    @Before
    public void setUp() {
    }


    @Test(expected = UserNotFoundException.class)
    public void follow_withUserNull_shouldThrowException() {
        UserServiceModel userToFollow = mock(UserServiceModel.class);
        this.userFollowServices.follow(null, userToFollow);
    }

    @Test(expected = UserNotFoundException.class)
    public void follow_withUserToFollowNull_shouldThrowException() {
        UserServiceModel user = mock(UserServiceModel.class);
        this.userFollowServices.follow(user, null);
    }

    @Test(expected = UserNotFoundException.class)
    public void follow_withUserAndUserToFollowNull_shouldThrowException() {
        this.userFollowServices.follow(null, null);
    }

    @Test(expected = UserCannotFollowException.class)
    public void follow_withUserSelfFollowing_shouldThrowException() {
        UserServiceModel user = mock(UserServiceModel.class);
        UserServiceModel userToFollow = mock(UserServiceModel.class);

        when(user.getId()).thenReturn("id");
        when(userToFollow.getId()).thenReturn("id");
        this.userFollowServices.follow(user, userToFollow);
    }

    @Test
    public void follow_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        UserServiceModel userToFollow = mock(UserServiceModel.class);
        UserFollowServiceModel userFollow = mock(UserFollowServiceModel.class);

        when(user.getId()).thenReturn("id");
        when(userToFollow.getId()).thenReturn("id1");
        doReturn(false).when(this.userFollowServices).isUserAlreadyFollowedUser(user, userToFollow);
        doReturn(userFollow).when(this.modelMapper).map(user, UserFollowServiceModel.class);
        this.userFollowServices.follow(user, userToFollow);

        verify(userFollow, times(1)).setSince(any());
        verify(userFollow, times(1)).setFollower(user);
        verify(userFollow, times(1)).setUser(userToFollow);
    }


    @Test(expected = UserNotFoundException.class)
    public void unfollow_withUserNull_shouldThrowException() {
        UserServiceModel userToUnfollow = mock(UserServiceModel.class);
        this.userFollowServices.unfollow(null, userToUnfollow);
    }

    @Test(expected = UserNotFoundException.class)
    public void unfollow__withUserToUnFollowNull_shouldThrowException() {
        UserServiceModel user = mock(UserServiceModel.class);
        this.userFollowServices.unfollow(user, null);
    }

    @Test(expected = UserNotFoundException.class)
    public void unfollow__withUserAndUserToUnfollowNull_shouldThrowException() {
        this.userFollowServices.unfollow(null, null);
    }

    @Test(expected = UserNotFollowingHimException.class)
    public void unfollow__withUserNotFollowing_shouldThrowException() {
        UserServiceModel user = mock(UserServiceModel.class);
        UserServiceModel userToUnfollow = mock(UserServiceModel.class);

        doReturn(false).when(this.userFollowServices).isUserAlreadyFollowedUser(user, userToUnfollow);
        this.userFollowServices.unfollow(user, userToUnfollow);
    }

    @Test
    public void unfollow_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        UserServiceModel userToUnfollow = mock(UserServiceModel.class);
        UserFollow userFollow = mock(UserFollow.class);
        UserFollowServiceModel userFollowServiceModel = mock(UserFollowServiceModel.class);
        User entityUser = mock(User.class);
        User entityUserToUnfollow = mock(User.class);

        when(this.modelMapper.map(user, User.class)).thenReturn(entityUser);
        when(this.modelMapper.map(userToUnfollow, User.class)).thenReturn(entityUserToUnfollow);
        doReturn(true).when(this.userFollowServices).isUserAlreadyFollowedUser(user, userToUnfollow);
        when(this.userFollowRepository.findByUserAndHisFollower(entityUserToUnfollow, entityUser)).thenReturn(userFollow);
        when(this.modelMapper.map(userFollow, UserFollowServiceModel.class)).thenReturn(userFollowServiceModel);
        this.userFollowServices.unfollow(user, userToUnfollow);

        verify(this.userFollowServices, times(1)).isUserAlreadyFollowedUser(user, userToUnfollow);
        verify(this.modelMapper, times(1)).map(user, User.class);
        verify(this.modelMapper, times(1)).map(userToUnfollow, User.class);
        verify(this.userFollowRepository, times(1)).findByUserAndHisFollower(entityUserToUnfollow, entityUser);
        verify(this.modelMapper, times(1)).map(userFollow, UserFollowServiceModel.class);
        verify(this.userFollowServices, times(1)).delete(userFollowServiceModel);

    }

    @Test(expected = UserNotFoundException.class)
    public void isUserAlreadyFollowedUser_withUserNull_shouldThrowException() {
        UserServiceModel followedUser = mock(UserServiceModel.class);
        this.userFollowServices.isUserAlreadyFollowedUser(null, followedUser);
    }

    @Test(expected = UserNotFoundException.class)
    public void isUserAlreadyFollowedUser_withFollowedUserNull_shouldThrowException() {
        UserServiceModel user = mock(UserServiceModel.class);
        this.userFollowServices.isUserAlreadyFollowedUser(user, null);
    }

    @Test(expected = UserNotFoundException.class)
    public void isUserAlreadyFollowedUser_withUserAndFollowedUserNull_shouldThrowException() {
        this.userFollowServices.isUserAlreadyFollowedUser(null, null);
    }

    @Test
    public void isUserAlreadyFollowedUser_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        UserServiceModel followedUser = mock(UserServiceModel.class);
        User entityUser = mock(User.class);
        User entityFollowedUser = mock(User.class);

        when(this.modelMapper.map(user,User.class)).thenReturn(entityUser);
        when(this.modelMapper.map(followedUser,User.class)).thenReturn(entityFollowedUser);

        this.userFollowServices.isUserAlreadyFollowedUser(user, followedUser);

        verify(this.modelMapper,times(1)).map(user,User.class);
        verify(this.modelMapper,times(1)).map(followedUser,User.class);
        verify(this.userFollowRepository,times(1)).isUserAlreadyFollowedUser(entityUser,entityFollowedUser);
    }

    @Test
    public void findUserTotalFollowings_withValidData_shouldInvokeMethods(){
        UserServiceModel user = mock(UserServiceModel.class);
        User entityUser = mock(User.class);

        when(this.modelMapper.map(user,User.class)).thenReturn(entityUser);

        this.userFollowServices.findUserTotalFollowings(user);

        verify(this.modelMapper,times(1)).map(user,User.class);
        verify(this.userFollowRepository,times(1)).getUserTotalFollowings(entityUser);
    }

    @Test
    public void findUserTotalFollowers_withValidData_shouldInvokeMethods(){
        UserServiceModel user = mock(UserServiceModel.class);
        User entityUser = mock(User.class);

        when(this.modelMapper.map(user,User.class)).thenReturn(entityUser);

        this.userFollowServices.findUserTotalFollowers(user);

        verify(this.modelMapper,times(1)).map(user,User.class);
        verify(this.userFollowRepository,times(1)).getUserTotalFollowers(entityUser);
    }

    @Test
    public void getFollowers_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        User entityUser = mock(User.class);

        when(this.modelMapper.map(user, User.class)).thenReturn(entityUser);

        this.userFollowServices.getFollowers(user);

        verify(this.modelMapper, times(1)).map(user, User.class);
        verify(this.userFollowRepository, times(1)).findUserFollowers(entityUser);
    }

    @Test
    public void getFollowings_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        User entityUser = mock(User.class);

        when(this.modelMapper.map(user, User.class)).thenReturn(entityUser);

        this.userFollowServices.getFollowings(user);

        verify(this.modelMapper, times(1)).map(user, User.class);
        verify(this.userFollowRepository, times(1)).findUserFollowings(entityUser);
    }

}
