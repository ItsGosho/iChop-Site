package com.ichop.core.areas.report.services;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.report.domain.models.binding.ThreadReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.ThreadReportServiceModel;
import com.ichop.core.areas.report.repositories.ThreadReportRepository;
import com.ichop.core.areas.thread.domain.entities.Thread;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ThreadReportServicesIntegrationTests {

    @Autowired
    private ThreadReportRepository threadReportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThreadRepository threadRepository;


    private ModelMapper modelMapper;
    private EntityFactory entityFactory;
    private ThreadReportServices threadReportServices;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.threadReportServices = new ThreadReportServicesImp(this.modelMapper, this.threadReportRepository);
    }

    @Test
    public void create_withValidData_shouldReturnValidResult() {
        User user = this.entityFactory.createUser();
        this.userRepository.save(user);
        Thread thread = this.entityFactory.createThread();
        thread.setCreator(user);
        this.threadRepository.save(thread);

        ThreadReportCreateBindingModel bindingModel = new ThreadReportCreateBindingModel();
        bindingModel.setReason("reason");
        bindingModel.setThread(this.modelMapper.map(thread, ThreadServiceModel.class));
        bindingModel.setUser(this.modelMapper.map(user, UserServiceModel.class));

        ThreadReportServiceModel result = this.threadReportServices.create(bindingModel);

        assertEquals(result.getThread().getContent(),thread.getContent());
        assertEquals(result.getReason(),"reason");
        assertEquals(result.getUser().getUsername(),user.getUsername());
        assertTrue(result.getReportDate().isBefore(LocalDateTime.now()));
    }


    @Test
    public void findById_withNotExistingThreadReport_shouldReturnNull(){
        ThreadReportServiceModel threadReportServiceModel = this.threadReportServices.findById("id");

        assertNull(threadReportServiceModel);
    }

    @Test
    public void findAll_withNotPresentReports_shouldReturn0(){
        Pageable pageable = PageRequest.of(10,10);

        Page<ThreadReportServiceModel> result = this.threadReportServices.findAll(pageable);

        assertEquals(0,result.getNumberOfElements());
    }

}
