package com.ichop.core.areas.user.services;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.binding.UserUpdateProfileInformationBindingModel;
import com.ichop.core.areas.user.domain.models.service.UserInformationServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.repositories.UserInformationRepository;
import com.ichop.core.areas.user.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserInformationServicesIntegrationTests {

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper;
    private EntityFactory entityFactory;
    private UserInformationServices userInformationServices;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.userInformationServices = new UserInformationServicesImp(this.modelMapper, this.userInformationRepository);
    }

    @Test
    public void update_withNotPreviousUserInformationAndValidData_shouldCreateUserInformation() {
        User user = this.userRepository.save(this.entityFactory.createUser());

        UserUpdateProfileInformationBindingModel bindingModel = new UserUpdateProfileInformationBindingModel();
        bindingModel.setUser(this.modelMapper.map(user, UserServiceModel.class));
        bindingModel.setAboutYou("aboutYou");
        bindingModel.setStatusMessage("statusMessage");
        bindingModel.setBirthDate("2000-12-12");

        UserInformationServiceModel result = this.userInformationServices.update(bindingModel);

        assertEquals(result.getAboutYou(),bindingModel.getAboutYou());
        assertEquals(result.getStatusMessage(),bindingModel.getStatusMessage());
        assertEquals(result.getBirthDate(), LocalDate.parse("2000-12-12"));
    }
}
