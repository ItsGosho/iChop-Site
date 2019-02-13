package ichop.services.users;

import ichop.domain.entities.users.User;
import ichop.repository.user.UserRepository;
import ichop.service.role.UserRoles;
import ichop.service.user.UserBaseServicesImp;
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

import ichop.factories.user.UserFactory;
import ichop.factories.user.UserFactoryImp;
import ichop.factories.role.UserRoleFactory;
import ichop.factories.role.UserRoleFactoryImp;
import java.util.LinkedList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserBaseServicesTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserBaseServicesImp userBaseServicesImp;

    private UserFactory userFactory;
    private UserRoleFactory userRoleFactory;

    @Before
    public void setUp() {
        this.userRoleFactory = new UserRoleFactoryImp();
        this.userFactory = new UserFactoryImp(this.userRoleFactory);
    }

    @Test
    public void isEmail_withNotValidEmailValue_shouldReturnFalse() {

        final String INVALID_EMAIL_1 = "emailche@bg";
        final String INVALID_EMAIL_2 = "emailche.bg";
        final String INVALID_EMAIL_3 = "email@.gmail.bg";

        Assert.assertFalse(this.userBaseServicesImp.isEmail(INVALID_EMAIL_1));
        Assert.assertFalse(this.userBaseServicesImp.isEmail(INVALID_EMAIL_2));
        Assert.assertFalse(this.userBaseServicesImp.isEmail(INVALID_EMAIL_3));
    }

    @Test
    public void isEmail_withValidEmailValue_shouldReturnTrue() {
        final String VALID_EMAIL = "chushkopeka@gmail.com";

        Assert.assertTrue(this.userBaseServicesImp.isEmail(VALID_EMAIL));
    }

    @Test
    public void existsByUsername_withNotExistingUsername_shouldReturnFalse() {
        final String NOT_EXISTING_USERNAME = "gataka";

        Mockito.when(this.userRepository.findUserByUsername(NOT_EXISTING_USERNAME)).thenReturn(null);

        Assert.assertFalse(this.userBaseServicesImp.existsByUsername(NOT_EXISTING_USERNAME));
    }

    @Test
    public void existsByUsername_withExistingUsername_shouldReturnTrue() {
        final String EXISTING_USERNAME = "abra_kadabra";

        Mockito.when(this.userRepository.findUserByUsername(EXISTING_USERNAME)).thenReturn(new User());

        Assert.assertTrue(this.userBaseServicesImp.existsByUsername(EXISTING_USERNAME));
    }

    @Test
    public void getTotalUsers_withTwoUsersInTheDatabase_shouldReturnTwo() {
        List<User> fakeUsersList = new LinkedList<>();
        fakeUsersList.add(new User());
        fakeUsersList.add(new User());

        Mockito.when(this.userRepository.findAll()).thenReturn(fakeUsersList);

        Assert.assertEquals(2, this.userBaseServicesImp.getTotalUsers());
    }

    @Test
    public void getTotalUsers_withZeroUsersInTheDatabase_shouldReturnZero() {

        Mockito.when(this.userRepository.findAll()).thenReturn(new LinkedList<>());

        Assert.assertEquals(0, this.userBaseServicesImp.getTotalUsers());
    }

    @Test
    public void getUserByUsername_withExistingUser_shouldReturnValidUser() {

        final String USER_USERNAME = "chushka";
        final String USER_EMAIL = "chushka@abv.bg";
        final String USER_PASSWORD = "123";
        final UserRoles[] USER_ROLES = new UserRoles[]{UserRoles.USER};

        User user = this.userFactory.create(USER_USERNAME, USER_EMAIL, USER_PASSWORD, USER_ROLES);

        Mockito.when(this.userRepository.findUserByUsername(USER_USERNAME)).thenReturn(user);

        Assert.assertEquals(user, this.userBaseServicesImp.getUserByUsername(USER_USERNAME));
    }

    @Test
    public void getUserByEmail_withExistingUser_shouldReturnValidUser() {

        final String USER_USERNAME = "domatcho";
        final String USER_EMAIL = "domatcho@abv.bg";
        final String USER_PASSWORD = "123";
        final UserRoles[] USER_ROLES = new UserRoles[]{UserRoles.USER};

        User user = this.userFactory.create(USER_USERNAME, USER_EMAIL, USER_PASSWORD, USER_ROLES);

        Mockito.when(this.userRepository.findUserByEmail(USER_EMAIL)).thenReturn(user);

        Assert.assertEquals(user, this.userBaseServicesImp.getUserByEmail(USER_EMAIL));

    }
}
