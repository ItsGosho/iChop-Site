package com.ichop.core.areas.report.services;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.post.domain.entities.Post;
import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.post.repositories.PostRepository;
import com.ichop.core.areas.report.domain.models.binding.PostReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.PostReportServiceModel;
import com.ichop.core.areas.report.repositories.PostReportRepository;
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
public class PostReportServicesIntegrationTests {

    @Autowired
    private PostReportRepository postReportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    private ModelMapper modelMapper;
    private EntityFactory entityFactory;
    private PostReportServices postReportServices;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.postReportServices = new PostReportServicesImp(this.modelMapper, this.postReportRepository);
    }

    @Test
    public void create_withValidData_shouldReturnValidResult() {
        User user = this.entityFactory.createUser();
        this.userRepository.save(user);
        Post post = this.entityFactory.createPost();
        post.setCreator(user);
        this.postRepository.save(post);

        PostReportCreateBindingModel bindingModel = new PostReportCreateBindingModel();
        bindingModel.setReason("reason");
        bindingModel.setPost(this.modelMapper.map(post, PostServiceModel.class));
        bindingModel.setUser(this.modelMapper.map(user, UserServiceModel.class));

        PostReportServiceModel result = this.postReportServices.create(bindingModel);

        assertEquals(result.getPost().getContent(),post.getContent());
        assertEquals(result.getReason(),"reason");
        assertEquals(result.getUser().getUsername(),user.getUsername());
        assertTrue(result.getReportDate().isBefore(LocalDateTime.now()));
    }

}
