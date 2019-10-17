package com.ichop.core.areas.user.repository;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.role.domain.entities.UserRole;
import com.ichop.core.areas.role.domain.entities.UserRoles;
import com.ichop.core.areas.role.repositories.UserRoleRepository;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTests {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private EntityFactory entityFactory;

    @Before
    public void setUp() {
        this.entityFactory = new EntityFactory();
    }

    @Test
    public void updateLastOnline_withValidData_shouldUpdateIt() {
        User user = this.entityFactory.createUser();
        LocalDateTime oldLastOnline = LocalDateTime.of(2000, 7, 16, 20, 0, 0);
        user.setLastOnline(oldLastOnline);
        this.userRepository.save(user);

        this.userRepository.updateLastOnline(user, LocalDateTime.now());

        User result = this.userRepository.findUserByUsername(user.getUsername());

        assertTrue(result.getLastOnline().isAfter(oldLastOnline));
    }

    @Test
    public void updateLocation_withValidData_shouldUpdateIt() {
        User user = this.entityFactory.createUser();
        String oldLocation = "location";
        user.setLocation(oldLocation);
        this.userRepository.save(user);

        this.userRepository.updateLocation(user, "junglata");

        User result = this.userRepository.findUserByUsername(user.getUsername());

        assertTrue(!result.getLocation().equals(oldLocation));
    }

    @Test
    public void findUsersByUsernameContains_withNoUserContaining_shouldReturn0() {
        Pageable pageable = PageRequest.of(10, 10);

        Page<User> result = this.userRepository.findUsersByUsernameContains("noOneContainingWord", pageable);

        assertEquals(result.getTotalElements(), 0);
    }

    @Test
    public void findUsersByUsernameContains_withOneUserContaining_shouldReturn1() {
        String currentUsername = "iLoveSpaghetti";
        User user = this.entityFactory.createUser();
        user.setUsername(currentUsername);
        this.userRepository.save(user);
        Pageable pageable = PageRequest.of(10, 10);

        Page<User> result = this.userRepository.findUsersByUsernameContains("veS", pageable);

        assertEquals(result.getTotalElements(), 1);
    }

    @Test
    public void findUsersWhomHasRole_withOneUserHavingMODERATORRole_shouldReturn1() {
        Set<UserRole> userRoles = new LinkedHashSet<>();
        UserRole userRole = this.entityFactory.createUserRole(UserRoles.MODERATOR);
        this.userRoleRepository.save(userRole);
        userRoles.add(userRole);

        User user = this.entityFactory.createUser();
        user.setAuthorities(userRoles);
        this.userRepository.save(user);

        Pageable pageable = PageRequest.of(10, 10);

        Page<User> result = this.userRepository.findUsersWhomHasRole(UserRoles.MODERATOR.name(), pageable);

        assertEquals(result.getTotalElements(), 1);
    }


    @Test
    public void findUsersWhomHasRole_withNoOneHavingADMINRole_shouldReturn0() {
        Pageable pageable = PageRequest.of(10, 10);

        Page<User> result = this.userRepository.findUsersWhomHasRole(UserRoles.ADMIN.name(), pageable);

        assertEquals(result.getTotalElements(), 0);
    }

}
