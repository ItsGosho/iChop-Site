package com.ichop.core.areas.reaction.services;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.entities.ThreadReaction;
import com.ichop.core.areas.reaction.domain.models.binding.ThreadReactionCreateBindingModel;
import com.ichop.core.areas.reaction.domain.models.service.ThreadReactionServiceModel;
import com.ichop.core.areas.reaction.repositories.ThreadReactionRepository;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ThreadReactionServicesIntegrationTests {

    @Autowired
    private ThreadReactionRepository threadReactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThreadRepository threadRepository;

    private ModelMapper modelMapper;
    private EntityFactory entityFactory;
    private ThreadReactionServices threadReactionServices;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.threadReactionServices =
                new ThreadReactionServicesImp(this.modelMapper, this.threadReactionRepository);
    }

    @Test
    public void create_withValidData_shouldReturnValidResult() {
        UserServiceModel liker = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        liker.setUsername("username2");
        liker.setEmail("email2@domain.bg");
        liker = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(liker, User.class)), UserServiceModel.class);

        UserServiceModel commentCreator = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        commentCreator = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(commentCreator, User.class)), UserServiceModel.class);

        ThreadServiceModel thread = this.modelMapper.map(this.entityFactory.createThread(), ThreadServiceModel.class);
        thread.setCreator(commentCreator);
        thread = this.modelMapper.map(this.threadRepository.save(this.modelMapper.map(thread, Thread.class)), ThreadServiceModel.class);

        ThreadReactionCreateBindingModel bindingModel = new ThreadReactionCreateBindingModel();
        bindingModel.setReactionType(ReactionType.LIKE);
        bindingModel.setUser(liker);
        bindingModel.setThread(thread);

        ThreadReactionServiceModel result = this.threadReactionServices.create(bindingModel);

        assertEquals(result.getUser().getUsername(), liker.getUsername());
        assertEquals(result.getReactionType().name(), ReactionType.LIKE.name());
        assertTrue(result.getReactionDate().isBefore(LocalDateTime.now()));
        assertEquals(result.getThread().getContent(), thread.getContent());
    }

    @Test
    public void isLikedByUser_withUserLikedIt_shouldReturnTrue() {
        UserServiceModel liker = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        liker.setUsername("username2");
        liker.setEmail("email2@domain.bg");
        liker = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(liker, User.class)), UserServiceModel.class);

        UserServiceModel commentCreator = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        commentCreator = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(commentCreator, User.class)), UserServiceModel.class);

        ThreadServiceModel thread = this.modelMapper.map(this.entityFactory.createThread(), ThreadServiceModel.class);
        thread.setCreator(commentCreator);
        thread = this.modelMapper.map(this.threadRepository.save(this.modelMapper.map(thread, Thread.class)), ThreadServiceModel.class);

        ThreadReactionCreateBindingModel bindingModel = new ThreadReactionCreateBindingModel();
        bindingModel.setReactionType(ReactionType.LIKE);
        bindingModel.setUser(liker);
        bindingModel.setThread(thread);
        this.threadReactionServices.create(bindingModel);

        boolean result = this.threadReactionServices.isLikedByUser(liker, thread);

        assertTrue(result);
    }

    @Test
    public void isLikedByUser_withUserNotLikedIt_shouldReturnFalse() {
        UserServiceModel liker = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        liker.setUsername("username2");
        liker.setEmail("email2@domain.bg");
        liker = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(liker, User.class)), UserServiceModel.class);

        UserServiceModel commentCreator = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        commentCreator = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(commentCreator, User.class)), UserServiceModel.class);

        ThreadServiceModel thread = this.modelMapper.map(this.entityFactory.createThread(), ThreadServiceModel.class);
        thread.setCreator(commentCreator);
        thread = this.modelMapper.map(this.threadRepository.save(this.modelMapper.map(thread, Thread.class)), ThreadServiceModel.class);

        boolean result = this.threadReactionServices.isLikedByUser(liker, thread);

        assertFalse(result);
    }

    @Test
    public void findTotalReactionsByUserAndType_with0Present_shouldReturn0() {
        UserServiceModel liker = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        liker.setUsername("username2");
        liker.setEmail("email2@domain.bg");
        liker = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(liker, User.class)), UserServiceModel.class);

        UserServiceModel commentCreator = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        commentCreator = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(commentCreator, User.class)), UserServiceModel.class);

        ThreadServiceModel thread = this.modelMapper.map(this.entityFactory.createThread(), ThreadServiceModel.class);
        thread.setCreator(commentCreator);
        thread = this.modelMapper.map(this.threadRepository.save(this.modelMapper.map(thread, Thread.class)), ThreadServiceModel.class);
        int result = this.threadReactionServices.findTotalReactionsByUserAndType(liker,ReactionType.LIKE);

        assertEquals(result,0);
    }

    @Test
    public void findTotalReactionsByUserAndType_with1Present_shouldReturn0() {
        UserServiceModel liker = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        liker.setUsername("username2");
        liker.setEmail("email2@domain.bg");
        liker = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(liker, User.class)), UserServiceModel.class);

        UserServiceModel commentCreator = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        commentCreator = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(commentCreator, User.class)), UserServiceModel.class);

        ThreadServiceModel thread = this.modelMapper.map(this.entityFactory.createThread(), ThreadServiceModel.class);
        thread.setCreator(commentCreator);
        thread = this.modelMapper.map(this.threadRepository.save(this.modelMapper.map(thread, Thread.class)), ThreadServiceModel.class);

        ThreadReaction threadReaction = this.entityFactory.createThreadReaction();
        threadReaction.setUser(this.modelMapper.map(liker, User.class));
        threadReaction.setThread(this.modelMapper.map(thread, Thread.class));
        this.threadReactionRepository.save(threadReaction);

        int result = this.threadReactionServices.findTotalReactionsByUserAndType(liker,ReactionType.LIKE);

        assertEquals(result,1);
    }

}
