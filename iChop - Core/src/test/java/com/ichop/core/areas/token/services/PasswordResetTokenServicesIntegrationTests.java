package com.ichop.core.areas.token.services;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.token.domain.models.binding.PasswordResetTokenCreateBindingModel;
import com.ichop.core.areas.token.domain.models.service.PasswordResetTokenServiceModel;
import com.ichop.core.areas.token.repositories.PasswordResetTokenRepository;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class PasswordResetTokenServicesIntegrationTests {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper;
    private EntityFactory entityFactory;
    private PasswordResetTokenServices passwordResetTokenServices;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.passwordResetTokenServices = new PasswordResetTokenServicesImp(this.modelMapper, this.passwordResetTokenRepository);
    }

    @Test
    public void create_withValidData_shouldReturnValidResult(){
        User user = this.userRepository.save(this.entityFactory.createUser());

        PasswordResetTokenCreateBindingModel bindingModel = new PasswordResetTokenCreateBindingModel();
        bindingModel.setUser(this.modelMapper.map(user, UserServiceModel.class));

        PasswordResetTokenServiceModel result = this.passwordResetTokenServices.create(bindingModel);

        assertEquals(result.getUser().getUsername(),user.getUsername());
        assertTrue(result.getExpiryDate().isAfter(LocalDateTime.now()));
        assertNotNull(result.getToken());
    }
    @Test
    public void deleteOldestByUser_withUserHavingOldestToken_shouldDeleteIt(){
        User user = this.userRepository.save(this.entityFactory.createUser());

        PasswordResetTokenCreateBindingModel bindingModel = new PasswordResetTokenCreateBindingModel();
        bindingModel.setUser(this.modelMapper.map(user, UserServiceModel.class));

        PasswordResetTokenServiceModel result = this.passwordResetTokenServices.create(bindingModel);

        this.passwordResetTokenServices.deleteOldestByUser(this.modelMapper.map(user,UserServiceModel.class));

        assertEquals(this.passwordResetTokenRepository.findAll().size(),0);
    }

    @Test
    public void findByToken_withExistingToken_shouldReturnIt(){
        User user = this.userRepository.save(this.entityFactory.createUser());

        PasswordResetTokenCreateBindingModel bindingModel = new PasswordResetTokenCreateBindingModel();
        bindingModel.setUser(this.modelMapper.map(user, UserServiceModel.class));

        PasswordResetTokenServiceModel result = this.passwordResetTokenServices.create(bindingModel);

        PasswordResetTokenServiceModel passwordResetToken = this.passwordResetTokenServices.findByToken(result.getToken());

        assertEquals(passwordResetToken.getId(),result.getId());
        assertEquals(passwordResetToken.getToken(),result.getToken());
        assertEquals(passwordResetToken.getExpiryDate(),result.getExpiryDate());
    }


}