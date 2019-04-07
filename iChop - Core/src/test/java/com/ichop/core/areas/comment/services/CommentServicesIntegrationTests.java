package com.ichop.core.areas.comment.services;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.comment.domain.models.binding.CommentCreateBindingModel;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.repositories.CommentRepository;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
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
public class CommentServicesIntegrationTests {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper;
    private EntityFactory entityFactory;
    private CommentServices commentServices;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.commentServices = new CommentServicesImp(this.modelMapper, this.commentRepository);
    }

    @Test
    public void create_withValidData_shouldReturnValidResult() {
        UserServiceModel user = this.modelMapper.map(this.entityFactory.createUser(),UserServiceModel.class);
        ThreadServiceModel thread = this.modelMapper.map(this.entityFactory.createThread(),ThreadServiceModel.class);
        CommentCreateBindingModel bindingModel = new CommentCreateBindingModel();
        bindingModel.setContent("content");
        bindingModel.setCreator(user);
        bindingModel.setThread(thread);

        CommentServiceModel result = this.commentServices.create(bindingModel);

        assertEquals(result.getCreator().getUsername(), user.getUsername());
        assertEquals(result.getThread().getTitle(), thread.getTitle());
        assertEquals(result.getContent(), bindingModel.getContent());
        assertTrue(result.getCreatedOn().isBefore(LocalDateTime.now()));
        assertEquals(result.getReactions().size(), 0);
        assertEquals(result.getReports().size(), 0);
    }

    @Test
    public void getTotalOfUser_with1Comment_shouldReturn1() {
        User user = this.entityFactory.createUser();
        this.userRepository.save(user);

        Comment comment = this.entityFactory.createComment();
        comment.setCreator(user);
        this.commentRepository.save(comment);

        int result = this.commentServices.getTotalOfUser(this.modelMapper.map(user,UserServiceModel.class));

        assertEquals(result,1);
    }

    @Test
    public void getTotalOfUser_with0Comments_shouldReturn0() {
        User user = this.entityFactory.createUser();
        this.userRepository.save(user);

        int result = this.commentServices.getTotalOfUser(this.modelMapper.map(user,UserServiceModel.class));

        assertEquals(result,0);
    }

    @Test
    public void findById_withCommentPresent_shouldReturnValidResult(){
        Comment comment = this.entityFactory.createComment();
        this.commentRepository.save(comment);

        CommentServiceModel result = this.commentServices.findById(comment.getId());

        assertEquals(comment.getId(),result.getId());
        assertEquals(comment.getContent(),result.getContent());
    }

    @Test
    public void findById_withNotExistingComment_shouldReturnNull(){
        CommentServiceModel result = this.commentServices.findById("id");

        assertNull(result);
    }

    @Test
    public void deleteById_withExistingCommentId_shouldDeleteIt(){
        Comment comment = this.entityFactory.createComment();
        this.commentRepository.save(comment);

        this.commentServices.delete(comment.getId());

        assertEquals(this.commentRepository.findAll().size(),0);
    }

    @Test
    public void deleteByModel_withExistingComment_shouldDeleteIt(){
        Comment comment = this.entityFactory.createComment();
        this.commentRepository.save(comment);

        this.commentServices.deleteByModel(this.modelMapper.map(comment,CommentServiceModel.class));

        assertEquals(this.commentRepository.findAll().size(),0);
    }

}
