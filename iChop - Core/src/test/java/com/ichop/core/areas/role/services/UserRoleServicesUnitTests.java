package com.ichop.core.areas.role.services;

import com.ichop.core.areas.role.domain.entities.UserRole;
import com.ichop.core.areas.role.domain.entities.UserRoles;
import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.role.exceptions.RoleNotFoundException;
import com.ichop.core.areas.role.repositories.UserRoleRepository;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleServicesImp userRoleServices;


    @Before
    public void setUp() {
    }

    @Test
    public void create_withExistingRole_shouldInvokeMethods() {
        UserRoles userRoles = UserRoles.OWNER;
        UserRoleServiceModel userRole = mock(UserRoleServiceModel.class);
        UserRole mockedRole = mock(UserRole.class);

        when(this.userRoleRepository.findUserRoleByAuthority(UserRoles.OWNER.name())).thenReturn(mockedRole);
        when(this.userRoleServices.findByAuthority(UserRoles.OWNER.name())).thenReturn(userRole);

        UserRoleServiceModel result = this.userRoleServices.create(userRoles);

        verify(this.userRoleRepository, times(2)).findUserRoleByAuthority(UserRoles.OWNER.name());
        assertEquals(userRole, result);
    }

    @Test
    public void create_withNotExistingRole_shouldInvokeMethods() {
        UserRoles userRoles = UserRoles.OWNER;

        UserRoleServiceModel result = this.userRoleServices.create(userRoles);

        verify(this.userRoleRepository, times(1)).findUserRoleByAuthority(UserRoles.OWNER.name());
        verify(this.userRoleRepository, times(1)).save(any());
    }

    @Test(expected = UserNotFoundException.class)
    public void findHighestOfUser_withNullUser_shouldThrowException() {
        this.userRoleServices.findHighestOfUser(null);
    }

    @Test
    public void findHighestOfUser_withTwoRoles_shouldReturnAdmin() {
        UserServiceModel user = new UserServiceModel();

        Set<UserRoleServiceModel> userRoleServiceModels = new HashSet<>();
        userRoleServiceModels.add(new UserRoleServiceModel() {{
            setAuthority(UserRoles.ADMIN.name());
        }});
        userRoleServiceModels.add(new UserRoleServiceModel() {{
            setAuthority(UserRoles.USER.name());
        }});

        user.setAuthorities(userRoleServiceModels);

        UserRoleServiceModel result = this.userRoleServices.findHighestOfUser(user);

        assertEquals(result.getAuthority(), UserRoles.ADMIN.name());
    }

    @Test
    public void findHighestOfUser_withOneRole_shouldReturnOneRole() {
        UserServiceModel user = new UserServiceModel();

        Set<UserRoleServiceModel> userRoleServiceModels = new HashSet<>();
        userRoleServiceModels.add(new UserRoleServiceModel() {{
            setAuthority(UserRoles.ADMIN.name());
        }});

        user.setAuthorities(userRoleServiceModels);

        UserRoleServiceModel result = this.userRoleServices.findHighestOfUser(user);

        assertEquals(result.getAuthority(), UserRoles.ADMIN.name());
    }

    @Test(expected = NullPointerException.class)
    public void findHighestOfUser_withNoRoles_shouldThrowException() {
        UserServiceModel user = new UserServiceModel();

        UserRoleServiceModel result = this.userRoleServices.findHighestOfUser(user);
    }

    @Test(expected = RoleNotFoundException.class)
    public void getUserNextRole_withNotExistingUserRole_shouldThrowException() {
        UserRoleServiceModel userRole = new UserRoleServiceModel();
        userRole.setAuthority("notExistingAuthority");

        this.userRoleServices.getUserNextRole(userRole);
    }

    @Test
    public void getUserNextRole_withNoNextRole_shouldReturnNull() {
        UserRoleServiceModel userRole = new UserRoleServiceModel();
        userRole.setAuthority(UserRoles.OWNER.name());

        UserRoleServiceModel result = this.userRoleServices.getUserNextRole(userRole);

        assertNull(result);
    }

    @Test
    public void getUserPreviousRole_withNoNextRole_shouldReturnNull() {
        UserRoleServiceModel userRole = new UserRoleServiceModel();
        userRole.setAuthority(UserRoles.USER.name());

        UserRoleServiceModel result = this.userRoleServices.getUserNextRole(userRole);

        assertNull(result);
    }

    @Test(expected = RoleNotFoundException.class)
    public void getUserPreviousRole_withNotExistingUserRole_shouldThrowException() {
        UserRoleServiceModel userRole = new UserRoleServiceModel();
        userRole.setAuthority("notExistingAuthority");

        this.userRoleServices.getUserPreviousRole(userRole);
    }

    @Test
    public void getUserPreviousRole_withNoPreviousRole_shouldReturnNull() {
        UserRoleServiceModel userRole = new UserRoleServiceModel();
        userRole.setAuthority(UserRoles.USER.name());

        UserRoleServiceModel result = this.userRoleServices.getUserNextRole(userRole);

        assertNull(result);
    }

    @Test
    public void findByAuthority_withNotExistingRole_shouldReturnNull() {
        when(this.userRoleRepository.findUserRoleByAuthority("authority")).thenReturn(null);

        UserRoleServiceModel result = this.userRoleServices.findByAuthority("authority");

        assertNull(result);
    }

    @Test
    public void findByAuthority_withExistingRole_shouldInvokeMethods() {
        UserRole userRole = mock(UserRole.class);
        when(this.userRoleRepository.findUserRoleByAuthority("authority")).thenReturn(userRole);

        UserRoleServiceModel result = this.userRoleServices.findByAuthority("authority");

        verify(this.modelMapper, times(1)).map(userRole, UserRoleServiceModel.class);
    }

}
