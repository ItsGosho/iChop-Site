package com.ichop.core.areas.user.services;

import com.ichop.core.areas.role.domain.entities.UserRoles;
import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.role.exceptions.RoleNotFoundException;
import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.token.domain.models.service.PasswordResetTokenServiceModel;
import com.ichop.core.areas.token.exceptions.TokenNotValidException;
import com.ichop.core.areas.token.services.PasswordResetTokenServices;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.binding.UserForgottenPasswordBindingModel;
import com.ichop.core.areas.user.domain.models.binding.UserRegisterBindingModel;
import com.ichop.core.areas.user.domain.models.binding.UserResetPasswordBindingModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.*;
import com.ichop.core.areas.user.repositories.UserRepository;
import com.ichop.core.components.email.EmailServices;
import com.ichop.core.components.jms.JmsServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordResetTokenServices passwordResetTokenServices;

    @Mock
    private EmailServices emailServices;

    @Mock
    private UserRoleServices userRoleServices;

    @Mock
    private JmsServices jmsServices;


    @Spy
    @InjectMocks
    private UserServicesImp userServices;


    @Before
    public void setUp() {
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadByUsername_withNotExistingUserByUsername_shouldThrowException() {

        when(this.userServices.isEmail("username")).thenReturn(false);
        doReturn(null).when(this.userServices).findUserByUsername("username");
        this.userServices.loadUserByUsername("username");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadByUsername_withNotExistingUserByEmail_shouldThrowException() {

        when(this.userServices.isEmail("email")).thenReturn(true);
        doReturn(null).when(this.userServices).findUserByEmail("email");
        this.userServices.loadUserByUsername("email");
    }

    @Test
    public void loadByUsername_withExistingUserByUsername_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);

        doReturn(user).when(this.userServices).findUserByUsername("username");
        when(this.userServices.isEmail("username")).thenReturn(false);
        this.userServices.loadUserByUsername("username");

        verify(this.modelMapper, times(1)).map(user, User.class);
    }

    @Test
    public void loadByUsername_withExistingUserByEmail_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);

        doReturn(user).when(this.userServices).findUserByEmail("email");
        when(this.userServices.isEmail("email")).thenReturn(true);
        this.userServices.loadUserByUsername("email");

        verify(this.modelMapper, times(1)).map(user, User.class);
    }


    @Test(expected = UserAlreadyExistsException.class)
    public void register_withUserExistingByUsername_shouldThrowException() {
        UserRegisterBindingModel bindingModel = mock(UserRegisterBindingModel.class);

        when(bindingModel.getUsername()).thenReturn("username");
        doReturn(true).when(this.userServices).isUserExistsByUsername("username");
        this.userServices.register(bindingModel);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void register_withUserExistingByEmail_shouldThrowException() {
        UserRegisterBindingModel bindingModel = mock(UserRegisterBindingModel.class);

        when(bindingModel.getUsername()).thenReturn("username");
        when(bindingModel.getEmail()).thenReturn("email");
        doReturn(false).when(this.userServices).isUserExistsByUsername("username");
        doReturn(true).when(this.userServices).isUserExistsByEmail("email");
        this.userServices.register(bindingModel);
    }

    @Test(expected = UserPasswordNotValidException.class)
    public void register_withUserNotMatchingPasswords_shouldThrowException() {
        UserRegisterBindingModel bindingModel = mock(UserRegisterBindingModel.class);

        when(bindingModel.getUsername()).thenReturn("username");
        when(bindingModel.getEmail()).thenReturn("email");
        when(bindingModel.getPassword()).thenReturn("password");
        when(bindingModel.getConfirmPassword()).thenReturn("notMatchingPassword");
        doReturn(false).when(this.userServices).isUserExistsByUsername("username");
        doReturn(false).when(this.userServices).isUserExistsByEmail("email");
        this.userServices.register(bindingModel);
    }

    @Test
    public void register_withValidData_shouldInvokeMethods() {
        UserRegisterBindingModel bindingModel = mock(UserRegisterBindingModel.class);
        UserServiceModel user = mock(UserServiceModel.class);

        String encodedPassword = "encodedPassword";

        when(bindingModel.getUsername()).thenReturn("username");
        when(bindingModel.getEmail()).thenReturn("email");
        when(bindingModel.getPassword()).thenReturn("password");
        when(bindingModel.getConfirmPassword()).thenReturn("password");
        doReturn(false).when(this.userServices).isUserExistsByUsername("username");
        doReturn(false).when(this.userServices).isUserExistsByEmail("email");
        when(this.modelMapper.map(bindingModel, UserServiceModel.class)).thenReturn(user);
        when(this.passwordEncoder.encode("password")).thenReturn(encodedPassword);
        doReturn(null).when(this.userServices).getInitialAuthorities();
        doReturn(null).when(this.userServices).save(user, UserServiceModel.class);

        this.userServices.register(bindingModel);

        verify(this.modelMapper, times(1)).map(bindingModel, UserServiceModel.class);
        verify(user, times(1)).setPassword(encodedPassword);
        verify(user, times(1)).setRegistrationDate(any());
        verify(user, times(1)).setAuthorities(null);
        verify(user, times(1)).setAccountNonExpired(true);
        verify(user, times(1)).setAccountNonLocked(true);
        verify(user, times(1)).setEnabled(true);
        verify(user, times(1)).setCredentialsNonExpired(true);
        verify(this.userServices, times(1)).save(user, UserServiceModel.class);

    }

    @Test
    public void sendUpdateAvatarRequest_withValidData_shouldInvokeMethods() {

        this.userServices.sendUpdateAvatarRequest("username", "binary64");

        verify(this.jmsServices, times(1)).sendModel(any(), any());
    }

    @Test
    public void getInitialAuthorities_with0TotalUsersPresented_shouldInvokeMethods() {

        doReturn(0L).when(this.userServices).findTotalUsers();
        this.userServices.getInitialAuthorities();

        verify(this.userRoleServices, times(1)).create(UserRoles.OWNER);
        verify(this.userRoleServices, times(1)).create(UserRoles.ADMIN);
        verify(this.userRoleServices, times(1)).create(UserRoles.MODERATOR);
        verify(this.userRoleServices, times(1)).create(UserRoles.USER);
    }

    @Test
    public void getInitialAuthorities_with1TotalUsersPresented_shouldInvokeMethods() {

        doReturn(1L).when(this.userServices).findTotalUsers();
        this.userServices.getInitialAuthorities();

        verify(this.userRoleServices, times(1)).create(UserRoles.USER);
    }

    @Test
    public void sendPasswordResetEmail_withValidData_shouldInvokeMethods() {
        UserForgottenPasswordBindingModel bindingModel = mock(UserForgottenPasswordBindingModel.class);

        UserDetails userDetails = mock(UserDetails.class);
        UserServiceModel user = mock(UserServiceModel.class);
        PasswordResetTokenServiceModel passwordResetToken = mock(PasswordResetTokenServiceModel.class);

        when(bindingModel.getUsernameOrEmail()).thenReturn("username");
        doReturn(userDetails).when(this.userServices).loadUserByUsername("username");
        when(this.modelMapper.map(userDetails, UserServiceModel.class)).thenReturn(user);
        when(this.passwordResetTokenServices.create(any())).thenReturn(passwordResetToken);
        doNothing().when(this.emailServices).sendResetPasswordEmail(any(), any(), any());

        this.userServices.sendPasswordResetEmail(bindingModel);

        verify(this.userServices, times(1)).loadUserByUsername("username");
        verify(this.modelMapper, times(1)).map(userDetails, UserServiceModel.class);
        verify(this.passwordResetTokenServices, times(1)).create(any());
        verify(this.emailServices, times(1)).sendResetPasswordEmail(any(), any(), any());
    }

    @Test(expected = TokenNotValidException.class)
    public void resetPasswordWithToken_withNotValidToken_shouldThrowException() {
        UserResetPasswordBindingModel bindingModel = mock(UserResetPasswordBindingModel.class);

        doReturn(false).when(this.passwordResetTokenServices).isValid("token");

        this.userServices.resetPassword(bindingModel, "token");
    }

    @Test(expected = UserPasswordNotValidException.class)
    public void resetPasswordWithToken_withNotEqualPasswords_shouldThrowException() {
        UserResetPasswordBindingModel bindingModel = mock(UserResetPasswordBindingModel.class);

        when(bindingModel.getPassword()).thenReturn("password");
        when(bindingModel.getConfirmPassword()).thenReturn("password1");
        doReturn(true).when(this.passwordResetTokenServices).isValid("token");

        this.userServices.resetPassword(bindingModel, "token");
    }

    @Test
    public void resetPasswordWithToken_withValidData_shouldInvokeMethods() {
        UserResetPasswordBindingModel bindingModel = mock(UserResetPasswordBindingModel.class);
        PasswordResetTokenServiceModel passwordResetToken = mock(PasswordResetTokenServiceModel.class);
        UserServiceModel user = mock(UserServiceModel.class);

        when(bindingModel.getPassword()).thenReturn("password");
        when(bindingModel.getConfirmPassword()).thenReturn("password");
        doReturn(passwordResetToken).when(this.passwordResetTokenServices).findByToken("token");
        when(passwordResetToken.getUser()).thenReturn(user);
        doNothing().when(this.passwordResetTokenServices).deleteOldestByUser(user);
        doReturn(null).when(this.userServices).save(user, UserServiceModel.class);
        doReturn(true).when(this.passwordResetTokenServices).isValid("token");

        this.userServices.resetPassword(bindingModel, "token");

        verify(this.passwordResetTokenServices, times(1)).isValid("token");
        verify(this.passwordResetTokenServices, times(1)).findByToken("token");
        verify(this.passwordEncoder, times(1)).encode("password");
        verify(this.userServices, times(1)).save(user, UserServiceModel.class);
        verify(this.passwordResetTokenServices, times(1)).deleteOldestByUser(user);
    }

    @Test(expected = UserPasswordNotValidException.class)
    public void resetPassword_withNotEqualPasswords_shouldThrowException() {
        UserResetPasswordBindingModel bindingModel = mock(UserResetPasswordBindingModel.class);
        UserServiceModel user = mock(UserServiceModel.class);

        when(bindingModel.getPassword()).thenReturn("password");
        when(bindingModel.getConfirmPassword()).thenReturn("password1");

        this.userServices.resetPassword(bindingModel, user);
    }

    @Test
    public void resetPassword_withValidData_shouldThrowException() {
        UserResetPasswordBindingModel bindingModel = mock(UserResetPasswordBindingModel.class);
        UserServiceModel user = mock(UserServiceModel.class);

        when(bindingModel.getPassword()).thenReturn("password");
        when(bindingModel.getConfirmPassword()).thenReturn("password");
        doReturn(null).when(this.userServices).save(user, UserServiceModel.class);

        this.userServices.resetPassword(bindingModel, user);

        verify(this.passwordEncoder, times(1)).encode("password");
        verify(this.userServices, times(1)).save(user, UserServiceModel.class);
    }


    @Test(expected = UserNotFoundException.class)
    public void promote_withNotExistingUser_shouldThrowException() {
        UserServiceModel user = mock(UserServiceModel.class);

        when(user.getUsername()).thenReturn("username");
        doReturn(false).when(this.userServices).isUserExistsByUsername("username");

        this.userServices.promote(user);
    }

    @Test(expected = RoleNotFoundException.class)
    public void promote_withNoNextRole_shouldThrowException() {
        UserServiceModel user = mock(UserServiceModel.class);
        UserRoleServiceModel currentRole = mock(UserRoleServiceModel.class);
        UserRoleServiceModel nextRole = mock(UserRoleServiceModel.class);

        when(user.getUsername()).thenReturn("username");
        doReturn(true).when(this.userServices).isUserExistsByUsername("username");
        doReturn(currentRole).when(this.userRoleServices).findHighestOfUser(user);
        doReturn(null).when(this.userRoleServices).getUserNextRole(currentRole);

        this.userServices.promote(user);
    }

    @Test(expected = RoleNotFoundException.class)
    public void promote_withNextRoleOwner_shouldThrowException() {
        UserServiceModel user = mock(UserServiceModel.class);
        UserRoleServiceModel currentRole = mock(UserRoleServiceModel.class);
        UserRoleServiceModel nextRole = mock(UserRoleServiceModel.class);

        when(user.getUsername()).thenReturn("username");
        doReturn(true).when(this.userServices).isUserExistsByUsername("username");
        doReturn(currentRole).when(this.userRoleServices).findHighestOfUser(user);
        doReturn(nextRole).when(this.userRoleServices).getUserNextRole(currentRole);
        when(nextRole.getAuthority()).thenReturn(UserRoles.OWNER.name());

        this.userServices.promote(user);
    }

    @Test
    public void promote_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        UserRoleServiceModel currentRole = mock(UserRoleServiceModel.class);
        UserRoleServiceModel nextRole = mock(UserRoleServiceModel.class);

        when(user.getUsername()).thenReturn("username");
        doReturn(true).when(this.userServices).isUserExistsByUsername("username");
        doReturn(currentRole).when(this.userRoleServices).findHighestOfUser(user);
        doReturn(nextRole).when(this.userRoleServices).getUserNextRole(currentRole);
        when(nextRole.getAuthority()).thenReturn(UserRoles.MODERATOR.name());
        doNothing().when(this.userServices).createEvent(any(), any(), any());
        doReturn(null).when(this.userServices).save(user, UserServiceModel.class);

        this.userServices.promote(user);


        verify(this.userServices, times(1)).createEvent(any(), any(), any());
        verify(this.userServices, times(1)).save(user, UserServiceModel.class);
    }

    @Test(expected = UserNotFoundException.class)
    public void demote_withNotExistingUser_shouldThrowException() {
        UserServiceModel user = mock(UserServiceModel.class);

        when(user.getUsername()).thenReturn("username");
        doReturn(false).when(this.userServices).isUserExistsByUsername("username");

        this.userServices.demote(user);
    }

    @Test(expected = RoleNotFoundException.class)
    public void demote_withNoPreviousRole_shouldThrowException() {
        UserServiceModel user = mock(UserServiceModel.class);
        UserRoleServiceModel currentRole = mock(UserRoleServiceModel.class);
        UserRoleServiceModel previousRole = mock(UserRoleServiceModel.class);

        when(user.getUsername()).thenReturn("username");
        doReturn(true).when(this.userServices).isUserExistsByUsername("username");
        doReturn(currentRole).when(this.userRoleServices).findHighestOfUser(user);

        this.userServices.promote(user);
    }

    @Test
    public void demote_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        UserRoleServiceModel currentRole = mock(UserRoleServiceModel.class);
        UserRoleServiceModel previousRole = mock(UserRoleServiceModel.class);

        when(user.getUsername()).thenReturn("username");
        doReturn(true).when(this.userServices).isUserExistsByUsername("username");
        doReturn(currentRole).when(this.userRoleServices).findHighestOfUser(user);
        doReturn(previousRole).when(this.userRoleServices).getUserPreviousRole(currentRole);
        doNothing().when(this.userServices).createEvent(any(), any(), any());
        doReturn(null).when(this.userServices).save(user, UserServiceModel.class);

        this.userServices.demote(user);


        verify(this.userServices, times(1)).createEvent(any(), any(), any());
        verify(this.userServices, times(1)).save(user, UserServiceModel.class);
    }

    @Test
    public void findByUsername_withNotExistingUser_shouldReturnNull() {

        doReturn(false).when(this.userServices).isUserExistsByUsername("username");
        UserServiceModel user = this.userServices.findUserByUsername("username");

        assertNull(user);
    }

    @Test
    public void findByUsername_withExistingUser_shouldReturnInvokeMethods() {
        User existingUser = mock(User.class);

        when(this.userRepository.findUserByUsername("username")).thenReturn(existingUser);
        doReturn(true).when(this.userServices).isUserExistsByUsername("username");
        UserServiceModel user = this.userServices.findUserByUsername("username");

        verify(this.userRepository, times(1)).findUserByUsername("username");
        verify(this.modelMapper, times(1)).map(existingUser, UserServiceModel.class);
    }

    @Test
    public void findByEmail_withNotExistingUser_shouldReturnNull() {

        doReturn(false).when(this.userServices).isUserExistsByEmail("email");
        UserServiceModel user = this.userServices.findUserByEmail("email");

        assertNull(user);
    }

    @Test
    public void findByEmail_withExistingUser_shouldReturnInvokeMethods() {
        User existingUser = mock(User.class);

        when(this.userRepository.findUserByEmail("email")).thenReturn(existingUser);
        doReturn(true).when(this.userServices).isUserExistsByEmail("email");
        UserServiceModel user = this.userServices.findUserByEmail("email");

        verify(this.userRepository, times(1)).findUserByEmail("email");
        verify(this.modelMapper, times(1)).map(existingUser, UserServiceModel.class);
    }

    @Test
    public void isEmail_withValidEmail_shouldReturnTrue() {
        boolean result = this.userServices.isEmail("email@domain.bg");

        assertTrue(result);
    }

    @Test
    public void isEmail_withInvalidEmail_shouldReturnTrue() {
        boolean result = this.userServices.isEmail("notValidEmail");

        assertFalse(result);
    }

    @Test
    public void isUserExistsByUsername_withExistingUser_shouldReturnTrue() {
        User user = mock(User.class);

        when(this.userRepository.findUserByUsername("username")).thenReturn(user);
        boolean result = this.userServices.isUserExistsByUsername("username");

        assertTrue(result);
    }

    @Test
    public void isUserExistsByUsername_withNotExistingUser_shouldReturnTrue() {

        when(this.userRepository.findUserByUsername("username")).thenReturn(null);
        boolean result = this.userServices.isUserExistsByUsername("username");

        assertFalse(result);
    }

    @Test
    public void isUserExistsByEmail_withExistingUser_shouldReturnTrue() {
        User user = mock(User.class);

        when(this.userRepository.findUserByEmail("email")).thenReturn(user);
        boolean result = this.userServices.isUserExistsByEmail("email");

        assertTrue(result);
    }

    @Test
    public void isUserExistsByEmail_withNotExistingUser_shouldReturnTrue() {

        when(this.userRepository.findUserByEmail("email")).thenReturn(null);
        boolean result = this.userServices.isUserExistsByEmail("email");

        assertFalse(result);
    }

    @Test
    public void findTotalUsers_with1UserPResent_shouldReturn1() {
        List<User> linkedList = new LinkedList();
        linkedList.add(new User());
        when(this.userRepository.findAll()).thenReturn(linkedList);
        long result = this.userServices.findTotalUsers();

        assertEquals(result, 1);
    }

    @Test
    public void findUserById_withNotExistingUser_shouldReturnNull() {

        doReturn(null).when(this.userServices).findById("id", UserServiceModel.class);
        UserServiceModel result = this.userServices.findUserById("id");

        assertNull(result);
    }

    @Test
    public void findUserById_withExistingUser_shouldReturnValidResult() {
        UserServiceModel user = mock(UserServiceModel.class);

        doReturn(user).when(this.userServices).findById("id", UserServiceModel.class);
        UserServiceModel result = this.userServices.findUserById("id");

        assertEquals(user, result);
    }

    @Test
    public void updateLastOnline_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        User entityUser = mock(User.class);
        LocalDateTime lastOnline = LocalDateTime.now();

        when(this.modelMapper.map(user,User.class)).thenReturn(entityUser);
        this.userServices.updateLastOnline(user, lastOnline);

        verify(this.modelMapper,times(1)).map(user,User.class);
        verify(this.userRepository,times(1)).updateLastOnline(entityUser,lastOnline);

    }

    @Test
    public void updateUserLocation_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        User entityUser = mock(User.class);
        String userLocation = "location";

        when(this.modelMapper.map(user,User.class)).thenReturn(entityUser);
        this.userServices.updateUserLocation(user, userLocation);

        verify(this.modelMapper,times(1)).map(user,User.class);
        verify(this.userRepository,times(1)).updateLocation(entityUser,userLocation);

    }

    @Test
    public void findUsersByUsernameContains_shouldInvokeMethods(){
        Pageable pageable = mock(Pageable.class);
        Page<User> page = mock(Page.class);

        when(this.userRepository.findUsersByUsernameContains("word",pageable)).thenReturn(page);
        this.userServices.findUsersByUsernameContains("word",pageable);

        verify(this.userRepository,times(1)).findUsersByUsernameContains("word",pageable);
    }

    @Test
    public void findUsersWhomHasRole_shouldInvokeMethods(){
        Pageable pageable = mock(Pageable.class);
        Page<User> page = mock(Page.class);
        String role = UserRoles.USER.name();

        when(this.userRepository.findUsersWhomHasRole(role,pageable)).thenReturn(page);
        this.userServices.findUsersWhomHasRole(role,pageable);

        verify(this.userRepository,times(1)).findUsersWhomHasRole(role,pageable);
    }

    @Test
    public void findAll_shouldInvokeMethods(){
        Pageable pageable = mock(Pageable.class);

        doReturn(null).when(this.userServices).findAll(UserServiceModel.class,pageable);
        this.userServices.findAll(pageable);

        verify(this.userServices,times(1)).findAll(UserServiceModel.class,pageable);
    }

}
