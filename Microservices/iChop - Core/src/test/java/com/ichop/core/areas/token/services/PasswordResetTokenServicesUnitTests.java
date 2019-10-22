package com.ichop.core.areas.token.services;

import com.ichop.core.areas.token.domain.models.binding.PasswordResetTokenCreateBindingModel;
import com.ichop.core.areas.token.domain.models.service.PasswordResetTokenServiceModel;
import com.ichop.core.areas.token.repositories.PasswordResetTokenRepository;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PasswordResetTokenServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Spy
    @InjectMocks
    private PasswordResetTokenServicesImp passwordResetTokenServices;


    @Before
    public void setUp() {
    }

    @Test
    public void isValid_withNotExistingToken_shouldReturnFalse(){

        boolean result = this.passwordResetTokenServices.isValid("token");

        assertFalse(result);
    }

    @Test
    public void isValid_withTokenDateExpired_shouldReturnFalse(){
        PasswordResetTokenServiceModel passwordResetToken = mock(PasswordResetTokenServiceModel.class);

        doReturn(passwordResetToken).when(this.passwordResetTokenServices).findTokenByToken("token");
        doReturn(true).when(this.passwordResetTokenServices).isTokenDateExpired(passwordResetToken);
        boolean result = this.passwordResetTokenServices.isValid("token");

        assertFalse(result);
    }

    @Test
    public void isValid_withValidData_shouldReturnTrue(){
        PasswordResetTokenServiceModel passwordResetToken = mock(PasswordResetTokenServiceModel.class);

        doReturn(passwordResetToken).when(this.passwordResetTokenServices).findTokenByToken("token");
        doReturn(false).when(this.passwordResetTokenServices).isTokenDateExpired(passwordResetToken);
        boolean result = this.passwordResetTokenServices.isValid("token");

        assertTrue(result);
    }

    @Test
    public void create_withValidData_shouldInvokeMethods(){
        PasswordResetTokenCreateBindingModel bindingModel = mock(PasswordResetTokenCreateBindingModel.class);
        PasswordResetTokenServiceModel passwordResetToken = mock(PasswordResetTokenServiceModel.class);

        when(this.modelMapper.map(bindingModel,PasswordResetTokenServiceModel.class)).thenReturn(passwordResetToken);
        this.passwordResetTokenServices.create(bindingModel);

        verify(this.passwordResetTokenServices,times(1)).save(passwordResetToken,PasswordResetTokenServiceModel.class);
        verify(passwordResetToken,times(1)).setExpiryDate(any());
        verify(passwordResetToken,times(1)).setToken(any());
        verify(this.modelMapper,times(1)).map(bindingModel,PasswordResetTokenServiceModel.class);
    }

    @Test
    public void deleteOldestByUser_withNotExistingToken_shouldNotInvokeMethods(){
        UserServiceModel user = mock(UserServiceModel.class);
        PasswordResetTokenServiceModel passwordResetToken = mock(PasswordResetTokenServiceModel.class);
        this.passwordResetTokenServices.deleteOldestByUser(user);

        verify(this.passwordResetTokenServices,times(0)).delete(passwordResetToken);
    }

    @Test
    public void deleteOldestByUser_withExistingToken_shouldInvokeMethods(){
        UserServiceModel user = mock(UserServiceModel.class);
        PasswordResetTokenServiceModel passwordResetToken = mock(PasswordResetTokenServiceModel.class);

        doReturn(passwordResetToken).when(this.passwordResetTokenServices).findTokenByUser(user);
        this.passwordResetTokenServices.deleteOldestByUser(user);

        verify(this.passwordResetTokenServices,times(1)).delete(passwordResetToken);
    }

    @Test
    public void findByToken_withValidData_shouldInvokeMethods(){
        this.passwordResetTokenServices.findByToken("token");
        verify(this.passwordResetTokenServices,times(1)).findTokenByToken("token");
    }

}
