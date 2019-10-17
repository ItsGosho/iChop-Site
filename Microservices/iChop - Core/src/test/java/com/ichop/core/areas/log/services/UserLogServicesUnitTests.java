package com.ichop.core.areas.log.services;

import com.ichop.core.areas.log.domain.entities.UserLogType;
import com.ichop.core.areas.log.domain.models.binding.UserLogCreateBindingModel;
import com.ichop.core.areas.log.domain.models.service.UserLogServiceModel;
import com.ichop.core.areas.log.repositories.UserLogRepository;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
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
public class UserLogServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserLogRepository userLogRepository;

    @Spy
    @InjectMocks
    private UserLogServicesImp userLogServices;


    @Before
    public void setUp() {
    }

    @Test(expected = UserNotFoundException.class)
    public void create_withNullUser_shouldThrowException() {
        this.userLogServices.create(mock(UserLogCreateBindingModel.class));
    }

    @Test
    public void create_withValidParameters_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        UserLogServiceModel userLogServiceModel = mock(UserLogServiceModel.class);
        UserLogCreateBindingModel userLogCreateBindingModel = mock(UserLogCreateBindingModel.class);

        when(userLogCreateBindingModel.getUser()).thenReturn(user);
        when(this.modelMapper.map(userLogCreateBindingModel,UserLogServiceModel.class)).thenReturn(userLogServiceModel);
        this.userLogServices.create(userLogCreateBindingModel);

        verify(this.modelMapper,times(1)).map(userLogCreateBindingModel,UserLogServiceModel.class);
        verify(userLogServiceModel,times(1)).setHappenedOn(any());
        verify(this.userLogServices,times(1)).save(userLogServiceModel,UserLogServiceModel.class);
    }

    @Test(expected = UserNotFoundException.class)
    public void findAllByUserAndLogType_withNullUser_shouldThrowException(){
        this.userLogServices.findAllByUserAndLogType(null,UserLogType.ROLE_CHANGE);
    }

    @Test
    public void findAllByUserAndLogType_withValidParameters_shouldInvokeMethods(){
        User entityUser = mock(User.class);
        UserServiceModel user = mock(UserServiceModel.class);
        UserLogType logType = UserLogType.ROLE_CHANGE;

        when(this.modelMapper.map(user,User.class)).thenReturn(entityUser);
        this.userLogServices.findAllByUserAndLogType(user,logType);

        verify(this.modelMapper,times(1)).map(user, User.class);
        verify(this.userLogRepository,times(1)).findAllByUserAndLogType(entityUser,logType);
    }

}
