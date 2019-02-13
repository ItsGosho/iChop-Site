package ichop.services.tokens;

import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.entities.users.User;
import ichop.exceptions.user.UserException;
import ichop.factories.token.PasswordResetTokenFactory;
import ichop.factories.token.PasswordResetTokenFactoryImp;
import ichop.repository.token.PasswordResetTokenRepository;
import ichop.service.token.PasswordResetTokenServicesImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PasswordResetTokenServicesTests {

    @Mock
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @InjectMocks
    private PasswordResetTokenServicesImp passwordResetTokenServicesImp;

    private PasswordResetTokenFactory passwordResetTokenFactory;

    @Before
    public void setUp() {
        this.passwordResetTokenFactory = new PasswordResetTokenFactoryImp();
    }


    @Test
    public void isValid_withNotExistingToken_shouldReturnFalse() {
        final String NOT_EXISTING_TOKEN = "e9bb85c2-5d5a-470c-8062-82d50d90e61c";

        Mockito.when(this.passwordResetTokenServicesImp.getTokenByToken(NOT_EXISTING_TOKEN)).thenReturn(null);

        boolean result = this.passwordResetTokenServicesImp.isValid(NOT_EXISTING_TOKEN);

        Assert.assertFalse(result);
    }

    @Test
    public void isValid_withExpiredToken_shouldReturnFalse() {
        final String TOKEN = "e9bb85c2-5d5a-470c-8062-12d502320e61c";
        final LocalDateTime EXPIRY_DATE = LocalDateTime.of(1970, 1, 1, 1, 1, 1);
        final PasswordResetToken passwordResetToken = this.passwordResetTokenFactory.create(TOKEN, null, EXPIRY_DATE);

        Mockito.when(this.passwordResetTokenServicesImp.getTokenByToken(TOKEN)).thenReturn(passwordResetToken);

        boolean result = this.passwordResetTokenServicesImp.isValid(TOKEN);

        Assert.assertFalse(result);
    }

    @Test
    public void isValid_withValidToken_shouldReturnTrue() {
        final String TOKEN = "e9zz85c2-5d5a-470c-8362-12d50232712gf";
        final LocalDateTime EXPIRY_DATE = LocalDateTime.now().plusDays(100);
        final PasswordResetToken passwordResetToken = this.passwordResetTokenFactory.create(TOKEN, null, EXPIRY_DATE);

        Mockito.when(this.passwordResetTokenServicesImp.getTokenByToken(TOKEN)).thenReturn(passwordResetToken);

        boolean result = this.passwordResetTokenServicesImp.isValid(TOKEN);

        Assert.assertTrue(result);
    }


    @Test(expected = UserException.class)
    public void createToken_withNullUser_shouldThrowException() {
        this.passwordResetTokenServicesImp.createToken(null);
    }

    @Test
    public void createToken_withValidUser_shouldReturnValidToken() {
        final User USER = new User();

        PasswordResetToken passwordResetToken = this.passwordResetTokenServicesImp.createToken(USER);

        Assert.assertNotNull(passwordResetToken.getToken());
        Assert.assertNotNull(passwordResetToken.getExpiryDate());
        Assert.assertNotNull(passwordResetToken.getUser());
    }


    @Test
    public void deleteOldestToken_withUserNotHavingToken_shouldDoNothing() {
        final User USER = new User();

        Mockito.when(this.passwordResetTokenRepository.findByUser(USER)).thenReturn(null);

        this.passwordResetTokenServicesImp.deleteOldestToken(USER);

        Mockito.verify(this.passwordResetTokenRepository,Mockito.times(0)).delete(Mockito.any());
    }

    @Test
    public void deleteOldestToken_withUserHavingToken_shouldDeleteTheOldestOne() {
        final User USER = new User();
        final PasswordResetToken PASS_TOKEN = new PasswordResetToken();

        Mockito.when(this.passwordResetTokenRepository.findByUser(USER)).thenReturn(PASS_TOKEN);

        this.passwordResetTokenServicesImp.deleteOldestToken(USER);

        Mockito.verify(this.passwordResetTokenRepository,Mockito.times(1)).delete(PASS_TOKEN);
    }
}
