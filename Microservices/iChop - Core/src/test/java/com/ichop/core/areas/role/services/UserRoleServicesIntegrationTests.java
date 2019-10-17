package com.ichop.core.areas.role.services;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.role.domain.entities.UserRole;
import com.ichop.core.areas.role.domain.entities.UserRoles;
import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.role.repositories.UserRoleRepository;
import com.ichop.core.areas.user.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRoleServicesIntegrationTests {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper;
    private EntityFactory entityFactory;
    private UserRoleServices userRoleServices;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.userRoleServices = new UserRoleServicesImp(this.modelMapper, this.userRoleRepository);
    }

    @Test
    public void create_withValidData_shouldReturnValidResult() {
        UserRoles userRole = UserRoles.ADMIN;

        UserRoleServiceModel result = this.userRoleServices.create(userRole);

        assertEquals(result.getAuthority(), UserRoles.ADMIN.name());
    }

    @Test
    public void getUserNextRole_withValidData_shouldReturnValidResult(){
        UserRole moderatorRole = new UserRole();
        moderatorRole.setAuthority(UserRoles.MODERATOR.name());
        UserRole adminRole = new UserRole();
        adminRole.setAuthority(UserRoles.ADMIN.name());
        this.userRoleRepository.save(moderatorRole);
        this.userRoleRepository.save(adminRole);

        UserRoleServiceModel userRole = new UserRoleServiceModel();
        userRole.setAuthority(UserRoles.MODERATOR.name());
        UserRoleServiceModel result = this.userRoleServices.getUserNextRole(userRole);

        assertEquals(result.getAuthority(),UserRoles.ADMIN.name());
    }

    @Test
    public void getUserPreviousRole_withValidData_shouldReturnValidResult(){
        UserRole moderatorRole = new UserRole();
        moderatorRole.setAuthority(UserRoles.MODERATOR.name());
        UserRole adminRole = new UserRole();
        adminRole.setAuthority(UserRoles.ADMIN.name());
        this.userRoleRepository.save(moderatorRole);
        this.userRoleRepository.save(adminRole);

        UserRoleServiceModel userRole = new UserRoleServiceModel();
        userRole.setAuthority(UserRoles.ADMIN.name());
        UserRoleServiceModel result = this.userRoleServices.getUserPreviousRole(userRole);

        assertEquals(result.getAuthority(),UserRoles.MODERATOR.name());
    }

}
