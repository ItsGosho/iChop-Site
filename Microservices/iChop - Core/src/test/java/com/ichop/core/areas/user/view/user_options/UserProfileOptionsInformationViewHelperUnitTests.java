package com.ichop.core.areas.user.view.user_options;

import com.ichop.core.areas.user.domain.models.service.UserInformationServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_options.UserProfileOptionsInformationViewModel;
import com.ichop.core.areas.user.helpers.view.user_options.UserProfileOptionsInformationViewHelper;
import com.ichop.core.areas.user.services.UserInformationServices;
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
public class UserProfileOptionsInformationViewHelperUnitTests {

    @Mock
    private UserServices userServices;

    @Mock
    private UserInformationServices userInformationServices;

    @Mock
    private ModelMapper modelMapper;

    @Spy
    @InjectMocks
    private UserProfileOptionsInformationViewHelper userProfileOptionsInformationViewHelper;


    @Test
    public void create_withValidData_shouldInvokeMethods() {
        String username = "username";
        UserServiceModel user = mock(UserServiceModel.class);
        UserInformationServiceModel userInformation = mock(UserInformationServiceModel.class);
        UserProfileOptionsInformationViewModel userProfileOptionsInformation = mock(UserProfileOptionsInformationViewModel.class);

        when(user.getUsername()).thenReturn(username);
        when(this.userServices.findUserByUsername(username)).thenReturn(user);
        when(this.userInformationServices.getByUser(user)).thenReturn(userInformation);
        when(this.modelMapper.map(userInformation,UserProfileOptionsInformationViewModel.class)).thenReturn(userProfileOptionsInformation);

        this.userProfileOptionsInformationViewHelper.create(username);

        verify(userProfileOptionsInformation,times(1)).setUsername(username);
        verify(this.userServices,times(1)).findUserByUsername(username);
        verify(this.userInformationServices,times(1)).getByUser(user);
        verify(this.modelMapper,times(1)).map(userInformation,UserProfileOptionsInformationViewModel.class);

    }


}
