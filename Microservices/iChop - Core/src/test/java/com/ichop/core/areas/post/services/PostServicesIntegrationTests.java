package com.ichop.core.areas.post.services;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.post.domain.entities.Post;
import com.ichop.core.areas.post.domain.models.binding.PostCreateBindingModel;
import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.post.repositories.PostRepository;
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
public class PostServicesIntegrationTests {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper;
    private EntityFactory entityFactory;
    private PostServices postServices;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.postServices = new PostServicesImp(this.modelMapper, this.postRepository);
    }

    @Test
    public void create_withValidData_shouldReturnValidResult() {
        UserServiceModel user = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        this.userRepository.save(this.modelMapper.map(user, User.class));

        UserServiceModel creator = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        creator.setUsername("username2");
        creator.setEmail("email2@domain.bg");
        this.userRepository.save(this.modelMapper.map(creator, User.class));

        PostCreateBindingModel bindingModel = new PostCreateBindingModel();
        bindingModel.setContent("content");
        bindingModel.setUser(user);
        bindingModel.setCreator(creator);

        PostServiceModel result = this.postServices.create(bindingModel);

        assertEquals(result.getUser().getUsername(), user.getUsername());
        assertEquals(result.getCreator().getUsername(), creator.getUsername());
        assertEquals(result.getContent(), bindingModel.getContent());
        assertEquals(result.getReports().size(), 0);
        assertTrue(result.getCreatedOn().isBefore(LocalDateTime.now()));
    }

    @Test
    public void findByUser_with0PostsPresent_shouldReturn0() {
        UserServiceModel user = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        user = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(user, User.class)), UserServiceModel.class);

        int result = this.postServices.findByUser(user).size();

        assertEquals(result, 0);
    }

    @Test
    public void findByUser_with1PostPresent_shouldReturn1() {
        UserServiceModel user = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        user = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(user, User.class)), UserServiceModel.class);

        Post post = this.entityFactory.createPost();
        post.setUser(this.modelMapper.map(user, User.class));
        this.postRepository.save(post);

        int result = this.postServices.findByUser(user).size();

        assertEquals(result, 1);
    }

    @Test
    public void findById_withExistingPost_shouldReturnValidResult() {
        UserServiceModel user = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        user = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(user, User.class)), UserServiceModel.class);

        Post post = this.entityFactory.createPost();
        post.setUser(this.modelMapper.map(user, User.class));
        this.postRepository.save(post);

        PostServiceModel result = this.postServices.findById(post.getId());

        assertEquals(result.getId(), post.getId());
    }

    @Test
    public void delete_withValidData_shouldDeleteIt() {
        UserServiceModel user = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        user = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(user, User.class)), UserServiceModel.class);

        Post post = this.entityFactory.createPost();
        post.setUser(this.modelMapper.map(user, User.class));
        this.postRepository.save(post);

        this.postServices.deleteByModel(this.modelMapper.map(post, PostServiceModel.class));

        assertEquals(this.postRepository.findAll().size(), 0);
    }

}
