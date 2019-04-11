package com.ichop.core.areas.user.view.user_control;

import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_control.UserControlHomeViewModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.areas.user.helpers.view.user_control.UserControlHomeViewHelper;
import com.ichop.core.areas.user.services.UserServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControlHomeViewHelperUnitTests {


    @Mock
    private UserServices userServices;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRoleServices userRoleServices;

    @Spy
    @InjectMocks
    private UserControlHomeViewHelper userControlHomeViewHelper;

    @Test(expected = UserNotFoundException.class)
    public void create_withNotExistingUser_shouldThrowException(){
        String username = "username";

        when(this.userServices.findUserByUsername(username)).thenReturn(null);

        this.userControlHomeViewHelper.create(username);
    }

    @Test
    public void create_withValidData_shouldInvokeMethods(){
        String username = "username";
        UserServiceModel user = mock(UserServiceModel.class);
        UserRoleServiceModel userRole = mock(UserRoleServiceModel.class);
        UserControlHomeViewModel userControlHomeViewModel = mock(UserControlHomeViewModel.class);

        when(userRole.getAuthority()).thenReturn("authrotity");
        when(this.userServices.findUserByUsername(username)).thenReturn(user);
        when(this.userRoleServices.findHighestOfUser(user)).thenReturn(userRole);
        when(this.modelMapper.map(user,UserControlHomeViewModel.class)).thenReturn(userControlHomeViewModel);

        this.userControlHomeViewHelper.create(username);

        verify(userControlHomeViewModel,times(1)).setRole(userRole.getAuthority());
        verify(this.userServices,times(1)).findUserByUsername(username);
        verify(this.userRoleServices,times(1)).findHighestOfUser(user);
        verify(this.modelMapper,times(1)).map(user,UserControlHomeViewModel.class);
    }

}
