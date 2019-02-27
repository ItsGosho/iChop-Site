package ichop.services.user;

import ichop.domain.entities.users.User;
import ichop.domain.models.service.UserServiceModel;
import ichop.repository.user.UserRepository;
import ichop.service.user.crud.UserCrudServicesImp;
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

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserCrudServicesTests {


    @Spy
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserCrudServicesImp userCrudServicesImp;


    @Before
    public void setUp() {

    }


    // is email

    @Test(expected = NullPointerException.class)
    public void isEmail_withNullValue_shouldThrowException() {
        String email = null;

        this.userCrudServicesImp.isEmail(email);
    }

    @Test
    public void isEmail_withoutAtSymbol_shouldReturnFalse(){
        String email = "usernamegmail.com";

        boolean result = this.userCrudServicesImp.isEmail(email);

        assertFalse(result);
    }

    @Test
    public void isEmail_withoutHost_shouldReturnFalse(){
        String email = "username@.com";

        boolean result = this.userCrudServicesImp.isEmail(email);

        assertFalse(result);
    }

    @Test
    public void isEmail_withoutDomain_shouldReturnFalse(){
        String email = "username@gmail";

        boolean result = this.userCrudServicesImp.isEmail(email);

        assertFalse(result);
    }

    @Test
    public void isEmail_withoutUsername_shouldReturnFalse(){
        String email = "@gmail.com";

        boolean result = this.userCrudServicesImp.isEmail(email);

        assertFalse(result);
    }

    // existsByUsername

    @Test
    public void existsByUsername_withNotExistingUsername_shouldReturnFalse(){
        String username = "notExistingUsername";

        when(this.userRepository.findUserByUsername(username)).thenReturn(null);
        boolean result = this.userCrudServicesImp.existsByUsername(username);

        assertFalse(result);
    }

    @Test
    public void existsByUsername_withExistingUsername_shouldReturnTrue(){
        String username = "existingUsername";

        when(this.userRepository.findUserByUsername(username)).thenReturn(new User());
        boolean result = this.userCrudServicesImp.existsByUsername(username);

        assertTrue(result);
    }

    // existsByEmail

    @Test
    public void existsByEmail_withNotExistingEmail_shouldReturnFalse(){
        String email = "notExistingEmail@gmail.com";

        when(this.userRepository.findUserByEmail(email)).thenReturn(null);
        boolean result = this.userCrudServicesImp.existsByEmail(email);

        assertFalse(result);
    }

    @Test
    public void existsByEmail_withExistingEmail_shouldReturnTrue(){
        String email = "existingEmail@gmail.com";

        when(this.userRepository.findUserByEmail(email)).thenReturn(new User());
        boolean result = this.userCrudServicesImp.existsByEmail(email);

        assertTrue(result);
    }

    // getTotalUsers

    @Test
    public void getTotalUsers_with3UsersPresent_shouldReturn3(){
        long expectedCount = 3;

        when(this.userRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(new User(),new User(),new User())));
        long result = this.userCrudServicesImp.getTotalUsers();

        assertEquals(expectedCount,result);
    }

    @Test
    public void getTotalUsers_withZeroUsersPresent_shouldReturnZero(){
        long expectedCount = 0;

        when(this.userRepository.findAll()).thenReturn(new ArrayList<>());
        long result = this.userCrudServicesImp.getTotalUsers();

        assertEquals(expectedCount,result);
    }

    // getUserByUsername

    @Test
    public void getUserByUsername_withExistingUsername_shouldReturnValidUser(){
        String expectedUsername = "username";
        User user = new User();
        user.setUsername(expectedUsername);

        when(this.userRepository.findUserByUsername(expectedUsername)).thenReturn(user);
        UserServiceModel resultedUser = this.userCrudServicesImp.getUserByUsername(expectedUsername);

        assertEquals(expectedUsername,resultedUser.getUsername());
    }

    @Test
    public void getUserByUsername_withNotExistingUsername_shouldReturnNull(){
        String notExistingUsername = "username";

        when(this.userRepository.findUserByUsername(notExistingUsername)).thenReturn(null);
        UserServiceModel resultedUser = this.userCrudServicesImp.getUserByUsername(notExistingUsername);

        assertNull(resultedUser);
    }

    // getUserByEmail

    @Test
    public void getUserByEmail_withExistingEmail_shouldReturnValidUser(){
        String expectedEmail = "username@gmail.com";
        User user = new User();
        user.setEmail(expectedEmail);

        when(this.userRepository.findUserByEmail(expectedEmail)).thenReturn(user);
        UserServiceModel resultedUser = this.userCrudServicesImp.getUserByEmail(expectedEmail);

        assertEquals(expectedEmail,resultedUser.getEmail());
    }

    @Test
    public void getUserByEmail_withNotExistingEmail_shouldReturnNull(){
        String expectedEmail = "notExistingEmail@gmail.com";
        User user = new User();
        user.setEmail(expectedEmail);

        when(this.userRepository.findUserByEmail(expectedEmail)).thenReturn(null);
        UserServiceModel resultedUser = this.userCrudServicesImp.getUserByEmail(expectedEmail);

        assertNull(resultedUser);
    }


    // save

    @Test
    public void save_shouldInvokeRepositorySaveMethodOnlyOneTime(){
        UserServiceModel userServiceModel = new UserServiceModel();
        User user = new User();

        when(this.modelMapper.map(userServiceModel,User.class)).thenReturn(user);
        this.userCrudServicesImp.save(userServiceModel);

        verify(this.userRepository,times(1)).save(user);
    }
}
