package ichop.services.users;

import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserRole;
import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.domain.models.binding.user.UserRegisterBindingModel;
import ichop.domain.models.binding.user.UserResetPasswordBindingModel;
import ichop.exceptions.token.TokenException;
import ichop.exceptions.user.UserException;
import ichop.factories.user.UserFactory;
import ichop.factories.user.UserFactoryImp;
import ichop.factories.role.UserRoleFactory;
import ichop.factories.role.UserRoleFactoryImp;
import ichop.service.role.UserRoleServices;
import ichop.service.role.UserRoles;
import ichop.service.token.PasswordResetTokenServices;
import ichop.service.user.UserCrudServicesImp;
import ichop.service.user.UserServicesImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServicesTests {

    @Mock
    private UserCrudServicesImp baseServicesImp;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRoleServices userRoleServices;

    @Mock
    private PasswordResetTokenServices passwordResetTokenServices;

    @InjectMocks
    private UserServicesImp userServicesImp;

    private UserFactory userFactory;
    private UserRoleFactory userRoleFactory;

    @Before
    public void setUp() {
        this.userRoleFactory = new UserRoleFactoryImp();
        this.userFactory = new UserFactoryImp(this.userRoleFactory);
    }

    @Test
    public void loadByUsername_withUsername_shouldReturnValidUser() {

        final String USER_USERNAME = "voldemor";
        final String USER_EMAIL = "voldemor@abv.bg";
        final String USER_PASSWORD = "123";
        final UserRoles[] USER_ROLES = new UserRoles[]{UserRoles.USER};

        User user = this.userFactory.create(USER_USERNAME, USER_EMAIL, USER_PASSWORD, USER_ROLES);

        Mockito.when(this.baseServicesImp.getUserByUsername(USER_USERNAME)).thenReturn(user);
        User resultedUser = (User) this.userServicesImp.loadUserByUsername(USER_USERNAME);

        Assert.assertEquals(user, resultedUser);
    }

    @Test
    public void loadByUsername_withEmail_shouldReturnValidUser() {

        final String USER_USERNAME = "chushka";
        final String USER_EMAIL = "chushka@abv.bg";
        final String USER_PASSWORD = "123";
        final UserRoles[] USER_ROLES = new UserRoles[]{UserRoles.USER};

        User user = this.userFactory.create(USER_USERNAME, USER_EMAIL, USER_PASSWORD, USER_ROLES);

        Mockito.when(this.baseServicesImp.getUserByUsername(USER_EMAIL)).thenReturn(user);
        User resultedUser = (User) this.userServicesImp.loadUserByUsername(USER_EMAIL);

        Assert.assertEquals(user, resultedUser);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadByUsername_withNotExistingUsername_shouldThrowException() {

        final String NOT_EXISTING_USERNAME = "doggy123";

        this.userServicesImp.loadUserByUsername(NOT_EXISTING_USERNAME);

    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadByUsername_withNotExistingEmail_shouldThrowException() {

        final String NOT_EXISTING_EMAIL = "boombay12@abv.bg";

        this.userServicesImp.loadUserByUsername(NOT_EXISTING_EMAIL);

    }


    @Test(expected = UserException.class)
    public void register_withAlreadyExistingUserByUsername_shouldThrowException() {

        final String USER_REGISTER_BINDING_MODEL_USERNAME = "rottenTomato";
        final String USER_REGISTER_BINDING_MODEL_EMAIL = "zevs@olimp.bg";
        final String USER_REGISTER_BINDING_MODEL_PASSWORD = "123";
        final String USER_REGISTER_BINDING_MODEL_CONFIRM_PASSWORD = "123";

        UserRegisterBindingModel userRegisterBindingModel = this.userFactory.create(USER_REGISTER_BINDING_MODEL_USERNAME, USER_REGISTER_BINDING_MODEL_PASSWORD, USER_REGISTER_BINDING_MODEL_CONFIRM_PASSWORD, USER_REGISTER_BINDING_MODEL_EMAIL);

        Mockito.when(this.baseServicesImp.existsByUsername(USER_REGISTER_BINDING_MODEL_USERNAME)).thenReturn(true);

        this.userServicesImp.register(userRegisterBindingModel);
    }

    @Test(expected = UserException.class)
    public void register_withAlreadyExistingUserByEmail_shouldThrowException() {
        final String USER_REGISTER_BINDING_MODEL_USERNAME = "blackDragon";
        final String USER_REGISTER_BINDING_MODEL_EMAIL = "blackDragon@abv.bg";
        final String USER_REGISTER_BINDING_MODEL_PASSWORD = "123";
        final String USER_REGISTER_BINDING_MODEL_CONFIRM_PASSWORD = "123";

        UserRegisterBindingModel userRegisterBindingModel = this.userFactory.create(USER_REGISTER_BINDING_MODEL_USERNAME, USER_REGISTER_BINDING_MODEL_PASSWORD, USER_REGISTER_BINDING_MODEL_CONFIRM_PASSWORD, USER_REGISTER_BINDING_MODEL_EMAIL);

        Mockito.when(this.baseServicesImp.existsByUsername(USER_REGISTER_BINDING_MODEL_USERNAME)).thenReturn(false);
        Mockito.when(this.baseServicesImp.existsByEmail(USER_REGISTER_BINDING_MODEL_EMAIL)).thenReturn(true);

        this.userServicesImp.register(userRegisterBindingModel);
    }

    @Test(expected = UserException.class)
    public void register_withNotMatchingPasswords_shouldThrowException() {
        final String USER_REGISTER_BINDING_MODEL_USERNAME = "redQueen";
        final String USER_REGISTER_BINDING_MODEL_EMAIL = "redQueen@abv.bg";
        final String USER_REGISTER_BINDING_MODEL_PASSWORD = "123";
        final String USER_REGISTER_BINDING_MODEL_CONFIRM_PASSWORD = "1234";

        UserRegisterBindingModel userRegisterBindingModel = this.userFactory.create(USER_REGISTER_BINDING_MODEL_USERNAME, USER_REGISTER_BINDING_MODEL_PASSWORD, USER_REGISTER_BINDING_MODEL_CONFIRM_PASSWORD, USER_REGISTER_BINDING_MODEL_EMAIL);

        Mockito.when(this.baseServicesImp.existsByUsername(USER_REGISTER_BINDING_MODEL_USERNAME)).thenReturn(false);
        Mockito.when(this.baseServicesImp.existsByEmail(USER_REGISTER_BINDING_MODEL_EMAIL)).thenReturn(false);

        this.userServicesImp.register(userRegisterBindingModel);
    }

    @Test
    public void register_withFirstRegisteredUser_shouldHaveOwnerRank(){

        final String USER_REGISTER_BINDING_MODEL_USERNAME = "dark_slayer";
        final String USER_REGISTER_BINDING_MODEL_EMAIL = "dark_slayer@abv.bg";
        final String USER_REGISTER_BINDING_MODEL_PASSWORD = "123";
        final String USER_REGISTER_BINDING_MODEL_CONFIRM_PASSWORD = "123";
        final String EXAMPLE_HASHED_PASSWORD = "$2y$12$i92GDm/YWkmN2yyA9OdMCe3dop1uPuBMEgjZVVyq56tBilKXNRoky";

        UserRegisterBindingModel userRegisterBindingModel = this.userFactory.create(USER_REGISTER_BINDING_MODEL_USERNAME, USER_REGISTER_BINDING_MODEL_PASSWORD, USER_REGISTER_BINDING_MODEL_CONFIRM_PASSWORD, USER_REGISTER_BINDING_MODEL_EMAIL);
        UserRole ownerRole = new UserRole();
        ownerRole.setAuthority(UserRoles.OWNER.name());

        Mockito.when(this.baseServicesImp.existsByUsername(USER_REGISTER_BINDING_MODEL_USERNAME)).thenReturn(false);
        Mockito.when(this.baseServicesImp.existsByEmail(USER_REGISTER_BINDING_MODEL_EMAIL)).thenReturn(false);
        Mockito.when(this.modelMapper.map(userRegisterBindingModel,User.class)).thenReturn(new ModelMapper().map(userRegisterBindingModel,User.class));
        Mockito.when(this.passwordEncoder.encode(USER_REGISTER_BINDING_MODEL_PASSWORD)).thenReturn(EXAMPLE_HASHED_PASSWORD);
        Mockito.when(this.userRoleServices.create(UserRoles.OWNER)).thenReturn(ownerRole);

        User user = this.userServicesImp.register(userRegisterBindingModel);

        Assert.assertTrue(user.getAuthorities().contains(ownerRole));
    }

    @Test
    public void register_withSecondRegisteredUser_shouldHaveOnlyUserRank(){

        final String USER_REGISTER_BINDING_MODEL_USERNAME = "multi_faster";
        final String USER_REGISTER_BINDING_MODEL_EMAIL = "multi_faster@abv.bg";
        final String USER_REGISTER_BINDING_MODEL_PASSWORD = "123";
        final String USER_REGISTER_BINDING_MODEL_CONFIRM_PASSWORD = "123";
        final String EXAMPLE_HASHED_PASSWORD = "$2y$12$i92GDm/YA9OdMCe3dop1uPuBMWkmN2yyEgjZVVyq56tBilKXNRoky";

        UserRegisterBindingModel userRegisterBindingModel = this.userFactory.create(USER_REGISTER_BINDING_MODEL_USERNAME, USER_REGISTER_BINDING_MODEL_PASSWORD, USER_REGISTER_BINDING_MODEL_CONFIRM_PASSWORD, USER_REGISTER_BINDING_MODEL_EMAIL);
        UserRole userRole = new UserRole();
        userRole.setAuthority(UserRoles.USER.name());

        Mockito.when(this.baseServicesImp.existsByUsername(USER_REGISTER_BINDING_MODEL_USERNAME)).thenReturn(false);
        Mockito.when(this.baseServicesImp.existsByEmail(USER_REGISTER_BINDING_MODEL_EMAIL)).thenReturn(false);
        Mockito.when(this.modelMapper.map(userRegisterBindingModel,User.class)).thenReturn(new ModelMapper().map(userRegisterBindingModel,User.class));
        Mockito.when(this.passwordEncoder.encode(USER_REGISTER_BINDING_MODEL_PASSWORD)).thenReturn(EXAMPLE_HASHED_PASSWORD);
        Mockito.when(this.userRoleServices.create(UserRoles.USER)).thenReturn(userRole);

        User user = this.userServicesImp.register(userRegisterBindingModel);

        Assert.assertTrue(user.getAuthorities().contains(userRole));
    }


    @Test(expected = UsernameNotFoundException.class)
    public void sendPasswordResetEmail_withNotExistingUser_shouldThrowException(){
        final String USER_FORGOTTEN_PASSWORD_BINDING_MODEL_USERNAME = "bloch_bloch";

        UserForgottenPasswordBindingModel userForgottenPasswordBindingModel = this.userFactory.create(USER_FORGOTTEN_PASSWORD_BINDING_MODEL_USERNAME);

        this.userServicesImp.sendPasswordResetEmail(userForgottenPasswordBindingModel);
    }


    @Test(expected = TokenException.class)
    public void resetPassword_withInvalidToken_shouldThrowException(){

        final String FAKE_TOKEN = "987610f3-9958-421b-8286-d07b6718bzbc";

        Mockito.when(this.passwordResetTokenServices.isValid(FAKE_TOKEN)).thenReturn(false);

        this.userServicesImp.resetPassword(new UserResetPasswordBindingModel(),FAKE_TOKEN);
    }

    @Test(expected = UserException.class)
    public void resetPassword_withNotMatchingUserPasswords_shouldThrowException(){

        final String FAKE_TOKEN = "98763313-9958-sfs-8286-d07b6718bzbc";
        final String USER_RESET_PASSWORD_BINDING_MODEL_PASSWORD = "123";
        final String USER_RESET_PASSWORD_BINDING_MODEL_CONFIRM_PASSWORD = "1234";

        UserResetPasswordBindingModel userResetPasswordBindingModel = this.userFactory.create(USER_RESET_PASSWORD_BINDING_MODEL_PASSWORD,USER_RESET_PASSWORD_BINDING_MODEL_CONFIRM_PASSWORD);

        Mockito.when(this.passwordResetTokenServices.isValid(FAKE_TOKEN)).thenReturn(true);

        this.userServicesImp.resetPassword(userResetPasswordBindingModel,FAKE_TOKEN);
    }


}
