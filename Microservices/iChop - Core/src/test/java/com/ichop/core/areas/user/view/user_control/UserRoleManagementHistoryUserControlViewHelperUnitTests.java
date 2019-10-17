package com.ichop.core.areas.user.view.user_control;

import com.ichop.core.areas.log.domain.entities.UserLogType;
import com.ichop.core.areas.log.domain.models.service.UserLogServiceModel;
import com.ichop.core.areas.log.services.UserLogServices;
import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_control.UserRoleManagementHistoryUserControlViewModel;
import com.ichop.core.areas.user.helpers.view.user_control.UserRoleManagementHistoryUserControlViewHelper;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleManagementHistoryUserControlViewHelperUnitTests {


    @Mock
    private UserServices userServices;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserLogServices userLogServices;

    @Mock
    private UserRoleServices userRoleServices;

    @Spy
    @InjectMocks
    private UserRoleManagementHistoryUserControlViewHelper userRoleManagementHistoryUserControlViewHelper;

    @Test
    public void create_withValidData_shouldInvokeMethods(){
        String username = "username";
        UserServiceModel user = mock(UserServiceModel.class);
        UserLogServiceModel userLog = mock(UserLogServiceModel.class);
        UserRoleManagementHistoryUserControlViewModel userRoleManagementHistoryUserControlViewModel = mock(UserRoleManagementHistoryUserControlViewModel.class);
        List<UserLogServiceModel> userLogs = new LinkedList<>();
        userLogs.add(userLog);

        when(this.userServices.findUserByUsername(username)).thenReturn(user);
        when(this.userLogServices.findAllByUserAndLogType(user, UserLogType.ROLE_CHANGE)).thenReturn(userLogs);
        when(this.modelMapper.map(userLog, UserRoleManagementHistoryUserControlViewModel.class)).thenReturn(userRoleManagementHistoryUserControlViewModel);

        List<UserRoleManagementHistoryUserControlViewModel> result = this.userRoleManagementHistoryUserControlViewHelper.create(username);

        assertEquals(1,result.size());
        assertTrue(result.contains(userRoleManagementHistoryUserControlViewModel));
        verify(this.userLogServices,times(1)).findAllByUserAndLogType(user,UserLogType.ROLE_CHANGE);
        verify(this.userServices,times(1)).findUserByUsername(username);
        verify(this.modelMapper,times(1)).map(userLog, UserRoleManagementHistoryUserControlViewModel.class);
    }

}
