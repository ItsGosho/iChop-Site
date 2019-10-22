package com.ichop.core.areas.thread.fillers;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.thread.domain.models.binding.ThreadCreateBindingModel;
import com.ichop.core.areas.thread.web.fillers.ThreadBindingModelsFillers;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.repositories.UserRepository;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.areas.user.services.UserServicesImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ThreadBindingModelsFillersIntegrationTests {


    @Autowired
    private UserRepository userRepository;

    private UserServices userServices;
    private EntityFactory entityFactory;
    private ModelMapper modelMapper;

    private ThreadBindingModelsFillers threadBindingModelsFillers;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.userServices = new UserServicesImp(null, this.modelMapper, this.userRepository, null, null, null, null);
        this.threadBindingModelsFillers = new ThreadBindingModelsFillers(this.userServices);
    }

    @Test
    public void fill_withNotExistingUser_shouldSetCreatorParameterToNull(){
        ThreadCreateBindingModel threadCreateBindingModel = new ThreadCreateBindingModel();
        Principal principal = () -> "notExistingUsername";

        ThreadCreateBindingModel result = this.threadBindingModelsFillers.fill(threadCreateBindingModel,principal);

        assertNull(result.getCreator());
    }

    @Test
    public void fill_withValidData_shouldSetCreatorParameter(){
        User user = this.userRepository.save(this.entityFactory.createUser());
        ThreadCreateBindingModel threadCreateBindingModel = new ThreadCreateBindingModel();
        Principal principal = () -> user.getUsername();

        ThreadCreateBindingModel result = this.threadBindingModelsFillers.fill(threadCreateBindingModel,principal);

        assertEquals(user.getUsername(),result.getCreator().getUsername());
    }

    @Test
    public void fill_withValidData_shouldNotChangeTheBindingModel(){
        User user = this.userRepository.save(this.entityFactory.createUser());
        ThreadCreateBindingModel threadCreateBindingModel = new ThreadCreateBindingModel();
        threadCreateBindingModel.setTitle("title");
        threadCreateBindingModel.setContent("content");
        Principal principal = () -> user.getUsername();

        ThreadCreateBindingModel result = this.threadBindingModelsFillers.fill(threadCreateBindingModel,principal);

        assertEquals(threadCreateBindingModel.getTitle(),result.getTitle());
        assertEquals(threadCreateBindingModel.getContent(),result.getContent());
    }

}
