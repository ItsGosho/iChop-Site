package com.ichop.core.areas.user.view.user_profile;

import com.ichop.core.areas.user.domain.models.service.UserInformationServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_profile.UserInformationProfileViewModel;
import com.ichop.core.areas.user.helpers.view.user_profile.UserInformationProfileViewHelper;
import com.ichop.core.areas.user.services.UserInformationServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserInformationProfileViewHelperUnitTests {

    @Mock
    private UserInformationServices userInformationServices;

    @Mock
    private ModelMapper modelMapper;

    @Spy
    @InjectMocks
    private UserInformationProfileViewHelper userInformationProfileViewHelper;


    @Test
    public void create_withNotExistingUserInformation_shouldReturnNull() {
        UserServiceModel user = mock(UserServiceModel.class);

        when(this.userInformationServices.getByUser(user)).thenReturn(null);

        UserInformationProfileViewModel result = this.userInformationProfileViewHelper.create(user);

        assertNull(result);
    }

    @Test
    public void create_withExistingUserInformation_shouldReturnValidResult() {
        UserServiceModel user = mock(UserServiceModel.class);
        UserInformationServiceModel userInformationServiceModel = mock(UserInformationServiceModel.class);
        UserInformationProfileViewModel userInformationProfileViewModel = mock(UserInformationProfileViewModel.class);

        when(this.userInformationServices.getByUser(user)).thenReturn(userInformationServiceModel);
        when(this.modelMapper.map(userInformationServiceModel,UserInformationProfileViewModel.class)).thenReturn(userInformationProfileViewModel);

        UserInformationProfileViewModel result = this.userInformationProfileViewHelper.create(user);

        assertNotNull(result);
    }

}
