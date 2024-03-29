package com.ichop.core.areas.user.services;

import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.entities.UserInformation;
import com.ichop.core.areas.user.domain.models.binding.UserUpdateProfileInformationBindingModel;
import com.ichop.core.areas.user.domain.models.service.UserInformationServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.areas.user.repositories.UserInformationRepository;
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
public class UserInformationServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserInformationRepository userInformationRepository;

    @Mock
    private UserServices userServices;

    @Spy
    @InjectMocks
    private UserInformationServicesImp userInformationServices;


    @Before
    public void setUp() {
    }


    @Test(expected = UserNotFoundException.class)
    public void update_withNullUser_shouldThrowException() {
        UserUpdateProfileInformationBindingModel bindingModel = mock(UserUpdateProfileInformationBindingModel.class);

        this.userInformationServices.update(bindingModel);
    }

    @Test
    public void update_withValidData_shouldInvokeMethods() {
        UserUpdateProfileInformationBindingModel bindingModel = mock(UserUpdateProfileInformationBindingModel.class);
        UserServiceModel user = mock(UserServiceModel.class);
        User entityUser = mock(User.class);
        UserInformation entityUserInformation = mock(UserInformation.class);
        UserInformationServiceModel userInformation = mock(UserInformationServiceModel.class);

        when(bindingModel.getUser()).thenReturn(user);
        when(bindingModel.getBirthDate()).thenReturn("2000-12-07");
        when(entityUserInformation.getId()).thenReturn("id");
        when(this.modelMapper.map(bindingModel, UserInformationServiceModel.class)).thenReturn(userInformation);
        when(this.modelMapper.map(user, User.class)).thenReturn(entityUser);
        when(this.userInformationRepository.findByUser(entityUser)).thenReturn(entityUserInformation);

        this.userInformationServices.update(bindingModel);

        verify(this.modelMapper, times(1)).map(bindingModel, UserInformationServiceModel.class);
        verify(this.modelMapper, times(1)).map(user, User.class);
        verify(this.userInformationRepository, times(1)).findByUser(entityUser);
        verify(userInformation, times(1)).setBirthDate(any());
        verify(userInformation, times(1)).setId(entityUserInformation.getId());
    }

    @Test
    public void isInformationExistsByUser_shouldInvokeMethods(){
        UserServiceModel user = mock(UserServiceModel.class);
        User entityUser = mock(User.class);

        when(this.modelMapper.map(user,User.class)).thenReturn(entityUser);
        this.userInformationServices.isUserInformationExistsByUser(user);

        verify(this.modelMapper,times(1)).map(user,User.class);
        verify(this.userInformationRepository,times(1)).findByUser(entityUser);
    }


}
