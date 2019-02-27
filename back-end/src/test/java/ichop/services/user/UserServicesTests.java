package ichop.services.user;

import ichop.components.email.EmailServicesImp;
import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserRole;
import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.domain.models.binding.user.UserRegisterBindingModel;
import ichop.domain.models.binding.user.UserResetPasswordBindingModel;
import ichop.domain.models.service.PasswordResetTokenServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.exceptions.token.TokenNotValidException;
import ichop.exceptions.user.UserAlreadyExistsException;
import ichop.exceptions.user.UserPasswordNotValidException;
import ichop.service.role.UserRoleServices;
import ichop.service.role.UserRoles;
import ichop.service.token.PasswordResetTokenServices;
import ichop.service.token.crud.PasswordResetTokenCrudServicesImp;
import ichop.service.user.UserServicesImp;
import ichop.service.user.crud.UserCrudServicesImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServicesTests {

    @Mock
    private EmailServicesImp emailServicesImp;

    @Mock
    private UserCrudServicesImp userCrudServicesImp;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private PasswordResetTokenCrudServicesImp passwordResetTokenCrudServicesImp;

    @Spy
    private ModelMapper modelMapper;

    @Mock
    private UserRoleServices userRoleServices;

    @Mock
    private PasswordResetTokenServices passwordResetTokenServices;

    @InjectMocks
    private UserServicesImp userServicesImp;


    @Before
    public void setUp() {
    }

    //loadByUsername

    @Test
    public void loadByUsername_withExistingUserUsername_shouldLoadUserByUsernameAndReturnIt(){
        String username = "username";
        User user = new User();
        user.setUsername(username);
        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);

        when(this.userCrudServicesImp.getUserByUsername(username)).thenReturn(userServiceModel);
        when(this.modelMapper.map(userServiceModel,User.class)).thenReturn(user);

        UserDetails resultedUser = this.userServicesImp.loadUserByUsername(username);

        assertEquals(username,resultedUser.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadByUsername_withNotExistingUserWithUsername_shouldThrowException(){
        String username = "username";
        User user = new User();
        user.setUsername(username);

        when(this.userCrudServicesImp.getUserByUsername(username)).thenReturn(null);

        this.userServicesImp.loadUserByUsername(username);
    }

    @Test
    public void loadByUsername_withExistingUserWithEmail_shouldLoadUserByEmailAndReturnIt(){
        String username = "username";
        String email = "username@gmail.com";
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);

        when(this.userCrudServicesImp.isEmail(email)).thenReturn(true);
        when(this.userCrudServicesImp.getUserByEmail(email)).thenReturn(userServiceModel);
        when(this.modelMapper.map(userServiceModel,User.class)).thenReturn(user);

        UserDetails resultedUser = this.userServicesImp.loadUserByUsername(email);

        assertEquals(username,resultedUser.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadByUsername_withNotExistingUserWithEmail_shouldThrowException(){
        String email = "username@gmail.com";
        User user = new User();
        user.setEmail(email);

        when(this.userCrudServicesImp.isEmail(email)).thenReturn(true);

        this.userServicesImp.loadUserByUsername(email);
    }

    //register

    @Test(expected = UserPasswordNotValidException.class)
    public void register_withNotMatchingPasswords_shouldThrowException(){
        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setPassword("1");
        userRegisterBindingModel.setConfirmPassword("12");

        this.userServicesImp.register(userRegisterBindingModel);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void register_withAlreadyExistingUsername_shouldThrowException(){
        String username = "existingUsername";
        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setUsername(username);

        when(this.userCrudServicesImp.existsByUsername(username)).thenReturn(true);

        this.userServicesImp.register(userRegisterBindingModel);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void register_withAlreadyExistingEmail_shouldThrowException(){
        String email = "existingEmail";
        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setEmail(email);

        when(this.userCrudServicesImp.existsByEmail(email)).thenReturn(true);

        this.userServicesImp.register(userRegisterBindingModel);
    }

    @Test
    public void register_withValidParameters_shouldHaveEncodedPassword(){
        String password = "123";
        String encodedAndExpectedPassword = new BCryptPasswordEncoder().encode(password);

        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setPassword(password);
        userRegisterBindingModel.setConfirmPassword(password);


        when(this.passwordEncoder.encode(password)).thenReturn(encodedAndExpectedPassword);

        UserServiceModel registeredUser = this.userServicesImp.register(userRegisterBindingModel);

        assertEquals(encodedAndExpectedPassword,registeredUser.getPassword());
    }

    @Test
    public void register_firstRegisteredUser_shouldHaveAllRoles(){
        int allRolesSize = 4;
        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setPassword("123");
        userRegisterBindingModel.setConfirmPassword("123");

        Mockito.when(this.userCrudServicesImp.getTotalUsers()).thenReturn(0L);
        Mockito.when(this.userRoleServices.create(UserRoles.OWNER)).thenReturn(new UserRole());
        Mockito.when(this.userRoleServices.create(UserRoles.ADMIN)).thenReturn(new UserRole());
        Mockito.when(this.userRoleServices.create(UserRoles.MODERATOR)).thenReturn(new UserRole());
        Mockito.when(this.userRoleServices.create(UserRoles.USER)).thenReturn(new UserRole());

        UserServiceModel registeredUser = this.userServicesImp.register(userRegisterBindingModel);
        int resultedRolesSize = registeredUser.getAuthorities().size();

        assertEquals(allRolesSize,resultedRolesSize);
    }

    @Test
    public void register_secondRegisteredUser_shouldHaveOneRole(){
        int allRolesSize = 1;
        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setPassword("123");
        userRegisterBindingModel.setConfirmPassword("123");

        Mockito.when(this.userCrudServicesImp.getTotalUsers()).thenReturn(1L);
        Mockito.when(this.userRoleServices.create(UserRoles.USER)).thenReturn(new UserRole());

        UserServiceModel registeredUser = this.userServicesImp.register(userRegisterBindingModel);
        int resultedRolesSize = registeredUser.getAuthorities().size();

        assertEquals(allRolesSize,resultedRolesSize);
    }

    //sendPasswordResetEmail

    @Test(expected = UsernameNotFoundException.class)
    public void sendPasswordResetEmail_withNotExistingUserByUsername_shouldThrowException(){
        String username = "notExistingUsername";
        UserForgottenPasswordBindingModel userForgottenPasswordBindingModel = new UserForgottenPasswordBindingModel();
        userForgottenPasswordBindingModel.setUsernameOrEmail(username);

        this.userServicesImp.sendPasswordResetEmail(userForgottenPasswordBindingModel);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void sendPasswordResetEmail_withNotExistingUserByEmail_shouldThrowException(){
        String email = "notExistingEmail@gmail.com";
        UserForgottenPasswordBindingModel userForgottenPasswordBindingModel = new UserForgottenPasswordBindingModel();
        userForgottenPasswordBindingModel.setUsernameOrEmail(email);

        this.userServicesImp.sendPasswordResetEmail(userForgottenPasswordBindingModel);
    }

    @Test
    public void sendPasswordResetEmail_shouldInvokeEmailServicesOnlyOneTime(){
        String username = "exampleUsername";
        UserForgottenPasswordBindingModel userForgottenPasswordBindingModel = new UserForgottenPasswordBindingModel();
        userForgottenPasswordBindingModel.setUsernameOrEmail(username);

        when(this.userCrudServicesImp.getUserByUsername(username)).thenReturn(new UserServiceModel());
        when(this.userServicesImp.loadUserByUsername(username)).thenReturn(new User());
        when(this.passwordResetTokenServices.createToken(any())).thenReturn(new PasswordResetTokenServiceModel());

        this.userServicesImp.sendPasswordResetEmail(userForgottenPasswordBindingModel);

        verify(this.emailServicesImp,times(1)).sendResetPasswordEmail(any(),any(),any());
    }

    //resetPassword

    @Test(expected = TokenNotValidException.class)
    public void resetPassword_withInvalidToken_shouldThrowException(){
        String notValidToken = "imInvalidOrNotExisting";

        when(this.passwordResetTokenServices.isValid(notValidToken)).thenReturn(false);

        this.userServicesImp.resetPassword(new UserResetPasswordBindingModel(),notValidToken);
    }

    @Test(expected = UserPasswordNotValidException.class)
    public void resetPassword_withUserNotMatchingPasswords_shouldThrowException(){
        String token = "exampleToken";
        UserResetPasswordBindingModel userResetPasswordBindingModel = new UserResetPasswordBindingModel();
        userResetPasswordBindingModel.setPassword("1");
        userResetPasswordBindingModel.setPassword("12");

        when(this.passwordResetTokenServices.isValid(token)).thenReturn(true);

        this.userServicesImp.resetPassword(userResetPasswordBindingModel,token);
    }

    @Test
    public void resetPassword_shouldInvokeSaveMethodAndDeleteOldestTokenOnlyOneTime(){
        String token = "justToken";
        UserResetPasswordBindingModel userResetPasswordBindingModel = new UserResetPasswordBindingModel();
        userResetPasswordBindingModel.setPassword("1");
        userResetPasswordBindingModel.setConfirmPassword("1");

        UserServiceModel userServiceModel = new UserServiceModel();
        PasswordResetTokenServiceModel passwordResetTokenServiceModel = new PasswordResetTokenServiceModel();
        passwordResetTokenServiceModel.setUser(userServiceModel);

        when(this.passwordResetTokenServices.isValid(token)).thenReturn(true);
        when(this.passwordResetTokenCrudServicesImp.getTokenByToken(token)).thenReturn(passwordResetTokenServiceModel);

        this.userServicesImp.resetPassword(userResetPasswordBindingModel,token);

        verify(this.userCrudServicesImp,times(1)).save(any());
        verify(this.passwordResetTokenServices,times(1)).deleteOldestToken(any());

    }

}
