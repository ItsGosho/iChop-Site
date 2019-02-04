package ichop.services.user;

import ichop.components.email.EmailServices;
import ichop.components.email.EmailServicesImp;
import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserRole;
import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.domain.models.binding.user.UserRegisterBindingModel;
import ichop.exceptions.user.PasswordsDoesntMatchException;
import ichop.exceptions.user.UserAlreadyExistsException;
import ichop.exceptions.user.UserCannotBeNullException;
import ichop.repository.user.UserRepository;
import ichop.service.role.UserRoleServicesImp;
import ichop.service.role.UserRoles;
import ichop.service.token.PasswordResetTokenServicesImp;
import ichop.service.user.UserServices;
import ichop.service.user.UserServicesImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Stubber;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServicesTests {

    private static final String USER_USERNAME = "Bai Tosho";
    private static final String USER_EMAIL = "baitosho@abv.bg";
    public static final String USER_REGISTER_BINDING_HASED_PASSWORD = "HASHED123";

    private UserRegisterBindingModel validUserRegisterBindingModel;

    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailServicesImp emailServicesImp;

    @Mock
    private PasswordResetTokenServicesImp passwordResetTokenServicesImp;

    @Mock
    private ModelMapper modelMapperMocked;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private UserRoleServicesImp userRoleServicesImp;

    @InjectMocks
    private UserServicesImp userServicesImp;

    @Before
    public void setUp() {

        this.modelMapper = new ModelMapper();

        User user = new User();
        user.setUsername(USER_USERNAME);
        user.setEmail(USER_EMAIL);

        this.validUserRegisterBindingModel = new UserRegisterBindingModel();
        this.validUserRegisterBindingModel.setUsername("itsgosho");
        this.validUserRegisterBindingModel.setEmail("email@abv.bg");
        this.validUserRegisterBindingModel.setPassword("parola12345");
        this.validUserRegisterBindingModel.setConfirmPassword("parola12345");

        Mockito.when(this.modelMapperMocked.map(this.validUserRegisterBindingModel, User.class)).thenReturn(this.modelMapper.map(this.validUserRegisterBindingModel, User.class));
        Mockito.when(this.passwordEncoder.encode(this.validUserRegisterBindingModel.getPassword())).thenReturn(USER_REGISTER_BINDING_HASED_PASSWORD);

        Mockito.when(this.userRepository.findUserByEmail(USER_EMAIL)).thenReturn(user);
        Mockito.when(this.userRepository.findUserByUsername(USER_USERNAME)).thenReturn(user);
    }

    @Test
    public void loadByUsername_withValidUsername_shouldReturnValidUser() {
        User user = (User) this.userServicesImp.loadUserByUsername(USER_USERNAME);
        Assert.assertEquals(user.getUsername(), USER_USERNAME);
    }

    @Test
    public void loadByUsername_withValidEmail_shouldReturnValidUser() {
        User user = (User) this.userServicesImp.loadUserByUsername(USER_EMAIL);
        Assert.assertEquals(user.getEmail(), USER_EMAIL);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadByUsername_withNotExistingUsername_shouldThrowException() {
        User user = (User) this.userServicesImp.loadUserByUsername("whoops1232313");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadByUsername_withNotExistingEmail_shouldThrowException() {
        User user = (User) this.userServicesImp.loadUserByUsername("missing@abv.bg");
        Assert.assertNull(user);
    }

    //---

    @Test
    public void isEmail_withValidEmail_shouldReturnTrue() {

        boolean result = this.userServicesImp.isEmail("username@hostname.topdomain");
        boolean withSmallLength = this.userServicesImp.isEmail("u@h.t");

        Assert.assertTrue(result);
        Assert.assertTrue(withSmallLength);
    }

    @Test
    public void isEmail_withInvalidEmail_shouldReturnFalse() {

        boolean withMissingSign = this.userServicesImp.isEmail("usernamehostname.topdomain");
        boolean withoutTopDomain = this.userServicesImp.isEmail("username@hostname");
        boolean withoutHostname = this.userServicesImp.isEmail("username@.topdomain");

        Assert.assertFalse(withMissingSign);
        Assert.assertFalse(withoutTopDomain);
        Assert.assertFalse(withoutHostname);
    }

    //---

    @Test(expected = UserAlreadyExistsException.class)
    public void register_withAlreadyExistingUsername_shouldThrowException() {
        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setUsername(USER_USERNAME);
        this.userServicesImp.register(userRegisterBindingModel);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void register_withAlreadyExistingEmail_shouldThrowException() {
        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setEmail(USER_EMAIL);
        this.userServicesImp.register(userRegisterBindingModel);
    }

    @Test(expected = PasswordsDoesntMatchException.class)
    public void register_withDifferentPasswordAndConfirmPassword_shouldThrowException() {
        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setPassword("123");
        userRegisterBindingModel.setConfirmPassword("1234");
        this.userServicesImp.register(userRegisterBindingModel);
    }

    @Test
    public void register_withValidParameters_shouldEncodeThePassword() {

        Mockito.when(this.userRoleServicesImp.create(Mockito.any())).thenReturn(new UserRole());

        User registeredUser = this.userServicesImp.register(this.validUserRegisterBindingModel);

        Assert.assertEquals(USER_REGISTER_BINDING_HASED_PASSWORD, registeredUser.getPassword());
    }

    @Test
    public void register_withFirstRegisteredUser_shouldHaveOwnerRank() {
        UserRole ownerRole = new UserRole();
        ownerRole.setAuthority(UserRoles.OWNER.name());
        Mockito.when(this.userRoleServicesImp.create(UserRoles.OWNER)).thenReturn(ownerRole);

        User registeredUser = this.userServicesImp.register(this.validUserRegisterBindingModel);
        boolean isPresent = registeredUser.getAuthorities().contains(ownerRole);

        Assert.assertTrue(isPresent);
    }

    @Test
    public void register_withSecondRegisteredUser_shouldHaveOnlyUserRank() {
        UserRole userRole = new UserRole();
        userRole.setAuthority(UserRoles.USER.name());

        Mockito.when(this.userRoleServicesImp.create(UserRoles.USER)).thenReturn(userRole);
        Mockito.when(this.userRepository.findAll()).thenReturn(new LinkedList<>(Arrays.asList(new User())));

        User registeredUser = this.userServicesImp.register(this.validUserRegisterBindingModel);
        boolean isPresent = registeredUser.getAuthorities().contains(userRole);

        Assert.assertTrue(isPresent);
    }

    //---

    @Test(expected = UserCannotBeNullException.class)
    public void getRole_withNull_shouldThrowException() {
        this.userServicesImp.getRole(null);

    }

    @Test
    public void getRole_withOneRole_shouldReturnUser() {
        User user = new User();
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole();
        userRole.setAuthority(UserRoles.USER.name());
        userRoles.add(userRole);

        user.setAuthorities(userRoles);

        String highestRole = this.userServicesImp.getRole(user).getAuthority();

        Assert.assertEquals(UserRoles.USER.name(), highestRole);
    }

    @Test
    public void getRole_withUserModeratorAdmin_shouldReturnAdmin() {
        User user = new User();
        Set<UserRole> userRoles = new HashSet<>();

        UserRole userRole = new UserRole();
        userRole.setAuthority(UserRoles.USER.name());

        UserRole moderatorRole = new UserRole();
        moderatorRole.setAuthority(UserRoles.MODERATOR.name());

        UserRole adminRole = new UserRole();
        adminRole.setAuthority(UserRoles.ADMIN.name());

        userRoles.add(userRole);
        userRoles.add(moderatorRole);
        userRoles.add(adminRole);

        user.setAuthorities(userRoles);

        String highestRole = this.userServicesImp.getRole(user).getAuthority();

        Assert.assertEquals(UserRoles.ADMIN.name(), highestRole);
    }

    //---

    @Test
    public void getUserByUsername_withExistingUsername_shouldReturnValidResult() {
        User user = this.userServicesImp.getUserByUsername(USER_USERNAME);
        Assert.assertEquals(USER_USERNAME, user.getUsername());
    }

    @Test
    public void getUserByUsername_withNotExistingUsername_shouldReturnNull() {
        Assert.assertNull(this.userServicesImp.getUserByUsername("notExistinggggg"));
    }

    //---

    @Test
    public void getUserByEmail_withExistingEmail_shouldReturnValidResult() {
        User user = this.userServicesImp.getUserByEmail(USER_EMAIL);
        Assert.assertEquals(USER_EMAIL, user.getEmail());
    }

    @Test
    public void getUserByEmail_withNotExistingEmail_shouldReturnNull() {
        Assert.assertNull(this.userServicesImp.getUserByEmail("notExistinggggg"));
    }

    //---

    @Test(expected = UserCannotBeNullException.class)
    public void sendPasswordResetEmail_CHANGE_shouldThrowException() {
        UserForgottenPasswordBindingModel userForgottenPasswordBindingModel = new UserForgottenPasswordBindingModel();
        userForgottenPasswordBindingModel.setUsernameOrEmail(USER_USERNAME);

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken("FAKE");
        passwordResetToken.setExpiryDate(LocalDateTime.now().plusDays(2));
        passwordResetToken.setUser(new User());

        Mockito.when(this.passwordResetTokenServicesImp.createToken(Mockito.any())).thenReturn(passwordResetToken);
        Mockito.doNothing().when(this.emailServicesImp).sendResetPasswordEmail(Mockito.anyString(),Mockito.anyString(),Mockito.any());

        this.userServicesImp.sendPasswordResetEmail(userForgottenPasswordBindingModel);

    }

    @Test(expected = UsernameNotFoundException.class)
    public void sendPasswordResetEmail_withNotExistingUser_shouldThrowException() {
        UserForgottenPasswordBindingModel userForgottenPasswordBindingModel = new UserForgottenPasswordBindingModel();
        userForgottenPasswordBindingModel.setUsernameOrEmail("notExistingUsername");

        this.userServicesImp.sendPasswordResetEmail(userForgottenPasswordBindingModel);

    }
}
