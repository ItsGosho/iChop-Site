package ichop.services.token;

import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.models.service.token.PasswordResetTokenServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.repository.token.PasswordResetTokenRepository;
import ichop.service.token.crud.PasswordResetTokenCrudServicesImp;
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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PasswordResetTokenCrudServicesTests {

    @Spy
    public ModelMapper modelMapper;

    @Mock
    public PasswordResetTokenRepository passwordResetTokenRepository;

    @InjectMocks
    public PasswordResetTokenCrudServicesImp passwordResetTokenCrudServicesImp;


    @Before
    public void setUp() {

    }

    //getTokenByUser

    @Test
    public void getTokenByUser_withExistingToken_shouldReturnToken() {
        String token = "existingToken";
        UserServiceModel userServiceModel = new UserServiceModel();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);

        when(this.passwordResetTokenRepository.findByUser(any())).thenReturn(passwordResetToken);
        PasswordResetTokenServiceModel passwordResetTokenServiceModel = this.passwordResetTokenCrudServicesImp.getTokenByUser(userServiceModel);

        assertEquals(passwordResetTokenServiceModel.getToken(), token);
    }

    @Test
    public void getTokenByUser_withoutPasswordResetToken_shouldReturnNull() {
        UserServiceModel userServiceModel = new UserServiceModel();

        when(this.passwordResetTokenRepository.findByUser(any())).thenReturn(null);
        PasswordResetTokenServiceModel passwordResetTokenServiceModel = this.passwordResetTokenCrudServicesImp.getTokenByUser(userServiceModel);

        assertNull(passwordResetTokenServiceModel);
    }

    //getTokenByToken

    @Test
    public void getTokenByToken_withExistingToken_shouldReturnToken() {
        String token = "existingToken";
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);

        when(this.passwordResetTokenRepository.findByToken(token)).thenReturn(passwordResetToken);
        PasswordResetTokenServiceModel result = this.passwordResetTokenCrudServicesImp.getTokenByToken(token);

        assertEquals(token, result.getToken());
    }

    @Test
    public void getTokenByToken_withNotExistingToken_shouldReturnNull() {
        String token = "notExistingToken";

        when(this.passwordResetTokenRepository.findByToken(token)).thenReturn(null);
        PasswordResetTokenServiceModel passwordResetTokenServiceModel = this.passwordResetTokenCrudServicesImp.getTokenByToken(token);

        assertNull(passwordResetTokenServiceModel);
    }

    //save

    @Test
    public void save_shouldInvokeRepositorySaveMethodOnlyOneTime() {
        this.passwordResetTokenCrudServicesImp.save(new PasswordResetTokenServiceModel());

        verify(this.passwordResetTokenRepository, times(1)).save(any());
    }

    //delete

    @Test
    public void delete_shouldInvokeRepositoryDeleteMethodOnlyOneTime() {
        this.passwordResetTokenCrudServicesImp.delete(new PasswordResetTokenServiceModel());

        verify(this.passwordResetTokenRepository, times(1)).delete(any());
    }



}
