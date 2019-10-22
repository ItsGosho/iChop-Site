package com.ichop.core.areas.user.view.user_control;

import com.ichop.core.areas.log.services.UserLogServices;
import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_control.UserRoleManagementHistoryUserControlViewModel;
import com.ichop.core.areas.user.domain.models.view.user_control.UserRoleManagementUserControlViewModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.areas.user.helpers.view.user_control.UserRoleManagementHistoryUserControlViewHelper;
import com.ichop.core.areas.user.helpers.view.user_control.UserRoleManagementUserControlViewHelper;
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
public class UserRoleManagementUserControlViewHelperUnitTests {


    @Mock
    private UserServices userServices;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserLogServices userLogServices;

    @Mock
    private UserRoleServices userRoleServices;

    @Mock
    private UserRoleManagementHistoryUserControlViewHelper userRoleManagementHistoryUserControlViewHelper;

    @Spy
    @InjectMocks
    private UserRoleManagementUserControlViewHelper userRoleManagementUserControlViewHelper;

    @Test(expected = UserNotFoundException.class)
    public void create_withNotExistingUser_shouldThrowException() {
        String username = "username";
        when(this.userServices.findUserByUsername(username)).thenReturn(null);

        this.userRoleManagementUserControlViewHelper.create(username);
    }

    @Test
    public void create_withValidData_shouldInvokeMethods() {
        String username = "username";
        UserServiceModel user = mock(UserServiceModel.class);
        UserRoleServiceModel highestRole = mock(UserRoleServiceModel.class);
        UserRoleServiceModel nextRole = mock(UserRoleServiceModel.class);
        UserRoleServiceModel previousRole = mock(UserRoleServiceModel.class);
        List<UserRoleManagementHistoryUserControlViewModel> userRoleManagementHistory = new LinkedList<>();
        UserRoleManagementUserControlViewModel result = mock(UserRoleManagementUserControlViewModel.class);

        when(this.userRoleServices.findHighestOfUser(user)).thenReturn(highestRole);
        when(highestRole.getAuthority()).thenReturn("MODERATOR");
        when(this.userRoleServices.getUserNextRole(this.userRoleServices.findHighestOfUser(user))).thenReturn(nextRole);
        when(nextRole.getAuthority()).thenReturn("ADMIN");
        when(this.userRoleServices.getUserPreviousRole(this.userRoleServices.findHighestOfUser(user))).thenReturn(previousRole);
        when(previousRole.getAuthority()).thenReturn("USER");
        when(this.userRoleManagementHistoryUserControlViewHelper.create(username)).thenReturn(userRoleManagementHistory);
        when(this.modelMapper.map(user, UserRoleManagementUserControlViewModel.class)).thenReturn(result);

        when(this.userServices.findUserByUsername(username)).thenReturn(user);

        this.userRoleManagementUserControlViewHelper.create(username);

        verify(result,times(1)).setRole(highestRole.getAuthority());
        verify(result,times(1)).setPreviousRole(previousRole.getAuthority());
        verify(result,times(1)).setNextRole(nextRole.getAuthority());
        verify(result,times(1)).setRoleChangeHistory(userRoleManagementHistory);
        verify(this.userRoleServices,times(8)).findHighestOfUser(user);
        verify(this.userRoleServices,times(8)).getUserNextRole(this.userRoleServices.findHighestOfUser(user));
        verify(this.userRoleServices,times(8)).getUserPreviousRole(this.userRoleServices.findHighestOfUser(user));
        verify(this.userRoleManagementHistoryUserControlViewHelper,times(1)).create(username);
        verify(this.modelMapper,times(1)).map(user,UserRoleManagementUserControlViewModel.class);

    }

}
