package com.ichop.core.areas.reaction.repository;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.comment.repositories.CommentRepository;
import com.ichop.core.areas.reaction.domain.entities.CommentReaction;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.repositories.CommentReactionRepository;
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class CommentReactionRepositoryTests {

    @Autowired
    private CommentReactionRepository commentReactionRepository;

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
    public void getUserTotalReactions_withUserNotHavingReactions_shouldReturn0() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.entityFactory.createComment();
        comment.setCreator(user);
        this.commentRepository.save(comment);

        int result = this.commentReactionRepository.getUserTotalReactions(user);

        assertEquals(result, 0);
    }

    @Test
    public void getUserTotalReactions_withUserHavingAReaction_shouldReturn1() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.entityFactory.createComment();
        comment.setCreator(user);
        this.commentRepository.save(comment);
        CommentReaction commentReaction = this.entityFactory.createCommentReaction(user, comment);
        this.commentReactionRepository.save(commentReaction);

        int result = this.commentReactionRepository.getUserTotalReactions(user);

        assertEquals(result, 1);
    }

    @Test
    public void getUserTotalReactionsWithReactionTypeLike_withUserHavingAReaction_shouldReturn1() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.entityFactory.createComment();
        comment.setCreator(user);
        this.commentRepository.save(comment);
        CommentReaction commentReaction = this.entityFactory.createCommentReaction(user, comment, ReactionType.LIKE);
        this.commentReactionRepository.save(commentReaction);

        int result = this.commentReactionRepository.getUserTotalReactions(user, ReactionType.LIKE);

        assertEquals(result, 1);
    }

    @Test
    public void getUserTotalReactionsWithReactionTypeDislike_withUserHavingAReaction_shouldReturn1() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.entityFactory.createComment();
        comment.setCreator(user);
        this.commentRepository.save(comment);
        CommentReaction commentReaction = this.entityFactory.createCommentReaction(user, comment, ReactionType.DISLIKE);
        this.commentReactionRepository.save(commentReaction);

        int result = this.commentReactionRepository.getUserTotalReactions(user, ReactionType.DISLIKE);

        assertEquals(result, 1);
    }

    @Test
    public void isUserReactedAtThatComment_withUserReactedAtThatComment_shouldReturnTrue() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.entityFactory.createComment();
        comment.setCreator(user);
        this.commentRepository.save(comment);
        CommentReaction commentReaction = this.entityFactory.createCommentReaction(user, comment);
        this.commentReactionRepository.save(commentReaction);

        boolean result = this.commentReactionRepository.isUserReactedAtThatComment(user,comment);

        assertTrue(result);
    }

    @Test
    public void isUserReactedAtThatComment_withUserNotReactedAtThatComment_shouldReturnFalse() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        Comment comment = this.entityFactory.createComment();
        comment.setCreator(user);
        this.commentRepository.save(comment);

        boolean result = this.commentReactionRepository.isUserReactedAtThatComment(user,comment);

        assertFalse(result);
    }

}
