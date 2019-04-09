package com.ichop.core.areas.thread.repository;

import com.ichop.core.EntityFactory;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ThreadRepositoryTests {


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
    public void increaseViews_withValidData_shouldIncreaseViewsWithONE(){
        User user = this.userRepository.save(this.entityFactory.createUser());
        Thread thread = this.entityFactory.createThread();
        thread.setCreator(user);
        this.threadRepository.save(thread);

        this.threadRepository.increaseViews(thread.getId());

        int result = this.threadRepository.findThreadById(thread.getId()).getViews();

        assertEquals(result,1);
    }

}
