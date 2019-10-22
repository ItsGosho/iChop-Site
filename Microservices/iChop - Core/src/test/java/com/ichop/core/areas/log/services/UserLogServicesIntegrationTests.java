package com.ichop.core.areas.log.services;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.log.domain.entities.UserLog;
import com.ichop.core.areas.log.domain.entities.UserLogType;
import com.ichop.core.areas.log.domain.models.binding.UserLogCreateBindingModel;
import com.ichop.core.areas.log.domain.models.service.UserLogServiceModel;
import com.ichop.core.areas.log.repositories.UserLogRepository;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserLogServicesIntegrationTests {

    @Autowired
    private UserLogRepository userLogRepository;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper;
    private EntityFactory entityFactory;
    private UserLogServices userLogServices;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.userLogServices = new UserLogServicesImp(this.modelMapper, this.userLogRepository);
    }

    @Test
    public void create_withValidData_shouldReturnValidResult(){
        UserServiceModel user = this.modelMapper.map(this.entityFactory.createUser(),UserServiceModel.class);
        this.userRepository.save(this.modelMapper.map(user, User.class));

        UserLogCreateBindingModel userLogCreateBindingModel = new UserLogCreateBindingModel();
        userLogCreateBindingModel.setLogType(UserLogType.ROLE_CHANGE);
        userLogCreateBindingModel.setUser(user);
        userLogCreateBindingModel.setMessage("message");

        UserLogServiceModel result = this.userLogServices.create(userLogCreateBindingModel);

        assertEquals(result.getMessage(),userLogCreateBindingModel.getMessage());
        assertEquals(result.getUser().getUsername(),user.getUsername());
        assertEquals(result.getLogType().name(),UserLogType.ROLE_CHANGE.name());
        assertTrue(result.getHappenedOn().isBefore(LocalDateTime.now()));
    }

    @Test
    public void findAllByUserAndLogType_withUser0LogsOfTypeRoleChange_shouldReturn0(){
        User user = this.entityFactory.createUser();
        user = this.userRepository.save(user);

        int result = this.userLogServices.findAllByUserAndLogType(this.modelMapper.map(user,UserServiceModel.class),UserLogType.ROLE_CHANGE).size();

        assertEquals(result,0);
    }

    @Test
    public void findAllByUserAndLogType_withUser1LogsOfTypeRoleChange_shouldReturn1(){
        User user = this.entityFactory.createUser();
        user = this.userRepository.save(user);

        UserLog userLog = this.entityFactory.createUserLog(UserLogType.ROLE_CHANGE);
        userLog.setUser(user);
        this.userLogRepository.save(userLog);

        int result = this.userLogServices
                .findAllByUserAndLogType(this.modelMapper.map(user,UserServiceModel.class),UserLogType.ROLE_CHANGE)
                .size();

        assertEquals(result,1);
    }

}
