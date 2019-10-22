package com.ichop.core.areas.reaction.repository;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.entities.ThreadReaction;
import com.ichop.core.areas.reaction.repositories.ThreadReactionRepository;
import com.ichop.core.areas.thread.domain.entities.Thread;
import com.ichop.core.areas.thread.repositories.ThreadRepository;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ThreadReactionRepositoryTests {

    @Autowired
    private ThreadReactionRepository threadReactionRepository;

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private UserRepository userRepository;

    private EntityFactory entityFactory;

    @Before
    public void setUp() {
        this.entityFactory = new EntityFactory();
    }


    @Test
    public void getUserTotalReactions_withUserNotHavingReactions_shouldReturn0(){
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.entityFactory.createThread();
        thread.setCreator(user);
        this.threadRepository.save(thread);

        int result = this.threadReactionRepository.getUserTotalReactions(user);

        assertEquals(result,0);
    }

    @Test
    public void getUserTotalReactions_withUserHavingAReaction_shouldReturn1(){
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.entityFactory.createThread();
        thread.setCreator(user);
        this.threadRepository.save(thread);
        ThreadReaction threadReaction = this.entityFactory.createThreadReaction(user,thread);
        this.threadReactionRepository.save(threadReaction);

        int result = this.threadReactionRepository.getUserTotalReactions(user);

        assertEquals(result,1);
    }

    @Test
    public void getUserTotalReactionsWithReactionTypeLike_withUserHavingAReaction_shouldReturn1(){
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.entityFactory.createThread();
        thread.setCreator(user);
        this.threadRepository.save(thread);
        ThreadReaction threadReaction = this.entityFactory.createThreadReaction(user,thread, ReactionType.LIKE);
        this.threadReactionRepository.save(threadReaction);

        int result = this.threadReactionRepository.getUserTotalReactions(user,ReactionType.LIKE);

        assertEquals(result,1);
    }

    @Test
    public void getUserTotalReactionsWithReactionTypeDislike_withUserHavingAReaction_shouldReturn1(){
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.entityFactory.createThread();
        thread.setCreator(user);
        this.threadRepository.save(thread);
        ThreadReaction threadReaction = this.entityFactory.createThreadReaction(user,thread, ReactionType.DISLIKE);
        this.threadReactionRepository.save(threadReaction);

        int result = this.threadReactionRepository.getUserTotalReactions(user,ReactionType.DISLIKE);

        assertEquals(result,1);
    }

    @Test
    public void isUserReactedAtThatThread_withUserReactedAtThatThread_shouldReturnTrue() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.entityFactory.createThread();
        thread.setCreator(user);
        this.threadRepository.save(thread);
        ThreadReaction threadReaction = this.entityFactory.createThreadReaction(user, thread);
        this.threadReactionRepository.save(threadReaction);

        boolean result = this.threadReactionRepository.isUserReactedAtThatThread(user,thread);

        assertTrue(result);
    }

    @Test
    public void isUserReactedAtThatThread_withUserNotReactedAtThatThread_shouldReturnFalse() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.entityFactory.createThread();
        thread.setCreator(user);
        this.threadRepository.save(thread);

        boolean result = this.threadReactionRepository.isUserReactedAtThatThread(user,thread);

        assertFalse(result);
    }

}
