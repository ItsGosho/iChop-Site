package ichop.services.token;

import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.models.service.PasswordResetTokenServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.service.token.PasswordResetTokenServicesImp;
import ichop.service.token.crud.PasswordResetTokenCrudServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PasswordResetTokenServicesTests {


    @Mock
    private PasswordResetTokenCrudServices passwordResetTokenCrudServices;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private PasswordResetTokenServicesImp passwordResetTokenServicesImp;

    @Before
    public void init() {

    }

    //isValid

    @Test
    public void isValid_withNotExistingToken_shouldReturnFalse() {
        String token = "notExistingToken";
        boolean result = this.passwordResetTokenServicesImp.isValid(token);

        Assert.assertFalse(result);
    }

    @Test
    public void isValid_withDateExpiredToken_shouldReturnFalse() {
        String token = "dateExpiredToken";
        PasswordResetTokenServiceModel passwordResetTokenServiceModel = new PasswordResetTokenServiceModel();
        passwordResetTokenServiceModel.setToken(token);
        passwordResetTokenServiceModel.setExpiryDate(LocalDateTime.of(1970, 1, 1, 0, 0, 0));

        when(this.passwordResetTokenCrudServices.getTokenByToken(token)).thenReturn(passwordResetTokenServiceModel);
        boolean result = this.passwordResetTokenServicesImp.isValid(token);

        Assert.assertFalse(result);
    }

    @Test
    public void isValid_withValidToken_shouldReturnTrue() {
        String token = "dateExpiredToken";
        PasswordResetTokenServiceModel passwordResetTokenServiceModel = new PasswordResetTokenServiceModel();
        passwordResetTokenServiceModel.setToken(token);
        passwordResetTokenServiceModel.setExpiryDate(LocalDateTime.of(3000, 1, 1, 0, 0, 0));

        when(this.passwordResetTokenCrudServices.getTokenByToken(token)).thenReturn(passwordResetTokenServiceModel);
        boolean result = this.passwordResetTokenServicesImp.isValid(token);

        Assert.assertTrue(result);
    }

    //createToken

    @Test
    public void createToken_withValidParameters_shouldReturnValidToken() {
        UserServiceModel userServiceModel = new UserServiceModel();

        PasswordResetTokenServiceModel passwordResetTokenServiceModel = this.passwordResetTokenServicesImp.createToken(userServiceModel);

        assertNotNull(passwordResetTokenServiceModel);
        assertNotNull(passwordResetTokenServiceModel.getToken());
        assertNotNull(passwordResetTokenServiceModel.getUser());
        assertNotNull(passwordResetTokenServiceModel.getExpiryDate());
    }

    @Test
    public void createToken_shouldInvokeSaveMethodOneTime() {

        this.passwordResetTokenServicesImp.createToken(new UserServiceModel());

        verify(this.passwordResetTokenCrudServices, times(1)).save(any());
    }


    //deleteOldestToken

    @Test
    public void deleteOldestToken_withUserNotHavingToken_shouldDoNothing() {

        this.passwordResetTokenServicesImp.deleteOldestToken(new UserServiceModel());

        verify(this.passwordResetTokenCrudServices, times(0)).delete(any());
    }

    @Test
    public void deleteOldestToken_shouldInvokeSaveMethod() {

        this.passwordResetTokenServicesImp.deleteOldestToken(new UserServiceModel());

        verify(this.passwordResetTokenCrudServices, times(0)).delete(any());
    }

    @Test
    public void deleteOldestToken_withUserHavingToken_shouldIvokeDeleteMethod() {
        UserServiceModel userServiceModel = new UserServiceModel();
        PasswordResetTokenServiceModel passwordResetTokenServiceModel = new PasswordResetTokenServiceModel();

        when(this.passwordResetTokenCrudServices.getTokenByUser(userServiceModel)).thenReturn(passwordResetTokenServiceModel);
        this.passwordResetTokenServicesImp.deleteOldestToken(userServiceModel);

        verify(this.passwordResetTokenCrudServices, times(1)).delete(passwordResetTokenServiceModel);
    }

}
