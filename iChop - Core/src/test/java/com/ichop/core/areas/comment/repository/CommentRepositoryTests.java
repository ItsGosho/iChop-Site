package com.ichop.core.areas.comment.repository;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.comment.repositories.CommentRepository;
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
public class CommentRepositoryTests {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    private EntityFactory entityFactory;

    @Before
    public void setUp() {
        this.entityFactory = new EntityFactory();
    }


    @Test
    public void getTotalCommentsOfUser_withUserNotHavingComments_shouldReturn0() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        int result = this.commentRepository.getTotalCommentsOfUser(user);

        assertEquals(0, result);
    }

    @Test
    public void getTotalCommentsOfUser_withUserHavingOneComment_shouldReturn1() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.entityFactory.createComment();
        comment.setCreator(user);
        this.commentRepository.save(comment);

        int result = this.commentRepository.getTotalCommentsOfUser(user);

        assertEquals(1,result);
    }

}
