package com.ichop.core.areas.thread.services;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.thread.domain.entities.Thread;
import com.ichop.core.areas.thread.domain.models.binding.ThreadCreateBindingModel;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.repositories.ThreadRepository;
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
public class ThreadServicesIntegrationTests {

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper;
    private EntityFactory entityFactory;
    private ThreadServices threadServices;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.threadServices = new ThreadServicesImp(this.modelMapper, this.threadRepository);
    }

    @Test
    public void create_withValidData_shouldCreateThread(){

        User user = this.userRepository.save(this.entityFactory.createUser());

        ThreadCreateBindingModel bindingModel = new ThreadCreateBindingModel();
        bindingModel.setTitle("title");
        bindingModel.setContent("content");
        bindingModel.setCreator(this.modelMapper.map(user, UserServiceModel.class));

        ThreadServiceModel result = this.threadServices.create(bindingModel);

        assertEquals(result.getTitle(),bindingModel.getTitle());
        assertEquals(result.getContent(),bindingModel.getContent());
        assertEquals(result.getCreator().getUsername(),user.getUsername());
        assertEquals((int) result.getViews(),0);
        assertTrue(result.getCreatedOn().isBefore(LocalDateTime.now()));

    }

    @Test
    public void finalAll_with1ThreadPresent_shouldReturn1(){

        Thread thread = this.entityFactory.createThread();
        this.threadRepository.save(thread);

        int size = this.threadServices.findAll().size();

        assertEquals(size,1);
    }

    @Test
    public void finalAll_with0ThreadPresent_shouldReturn0(){

        int size = this.threadServices.findAll().size();

        assertEquals(size,0);
    }

    @Test
    public void findById_withValidId_shouldReturnValidResult(){
        Thread thread = this.entityFactory.createThread();
        this.threadRepository.save(thread);

        ThreadServiceModel result = this.threadServices.findById(thread.getId());

        assertEquals(result.getId(),thread.getId());
        assertEquals(result.getTitle(),thread.getTitle());
    }

    @Test
    public void delete_withValidData_shouldDeleteThread(){
        Thread thread = this.entityFactory.createThread();
        this.threadRepository.save(thread);

        this.threadServices.delete(thread.getId());

        assertEquals(this.threadRepository.findAll().size(),0);
    }

    @Test
    public void isPresentById_withThreadPresent_shouldReturnTrue(){
        Thread thread = this.entityFactory.createThread();
        this.threadRepository.save(thread);

        boolean result = this.threadServices.isPresentById(thread.getId());

        assertTrue(result);
    }

    @Test
    public void isPresentById_withNotExistingThread_shouldReturnFalse(){
        boolean result = this.threadServices.isPresentById("notExistingId");

        assertFalse(result);
    }


}
