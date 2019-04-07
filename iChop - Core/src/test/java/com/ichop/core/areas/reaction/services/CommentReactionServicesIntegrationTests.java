package com.ichop.core.areas.reaction.services;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.repositories.CommentRepository;
import com.ichop.core.areas.reaction.domain.entities.CommentReaction;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.models.binding.CommentReactionCreateBindingModel;
import com.ichop.core.areas.reaction.domain.models.service.CommentReactionServiceModel;
import com.ichop.core.areas.reaction.repositories.CommentReactionRepository;
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
public class CommentReactionServicesIntegrationTests {

    @Autowired
    private CommentReactionRepository commentReactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    private ModelMapper modelMapper;
    private EntityFactory entityFactory;
    private CommentReactionServices commentReactionServices;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.commentReactionServices = new CommentReactionServicesImp(this.modelMapper, this.commentReactionRepository);
    }

    @Test
    public void create_withValidData_shouldReturnValidResult() {
        UserServiceModel liker = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        liker.setUsername("username2");
        liker.setEmail("email2@domain.bg");
        liker = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(liker, User.class)), UserServiceModel.class);

        UserServiceModel commentCreator = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        commentCreator = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(commentCreator, User.class)), UserServiceModel.class);

        CommentServiceModel comment = this.modelMapper.map(this.entityFactory.createComment(), CommentServiceModel.class);
        comment.setCreator(commentCreator);
        comment = this.modelMapper.map(this.commentRepository.save(this.modelMapper.map(comment, Comment.class)), CommentServiceModel.class);

        CommentReactionCreateBindingModel bindingModel = new CommentReactionCreateBindingModel();
        bindingModel.setReactionType(ReactionType.LIKE);
        bindingModel.setUser(liker);
        bindingModel.setComment(comment);

        CommentReactionServiceModel result = this.commentReactionServices.create(bindingModel);

        assertEquals(result.getUser().getUsername(), liker.getUsername());
        assertEquals(result.getReactionType().name(), ReactionType.LIKE.name());
        assertTrue(result.getReactionDate().isBefore(LocalDateTime.now()));
        assertEquals(result.getComment().getContent(), comment.getContent());
    }

    @Test
    public void isLikedByUser_withUserNotLikedThatComment_shouldReturnFalse() {
        UserServiceModel liker = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        liker.setUsername("username2");
        liker.setEmail("email2@domain.bg");
        liker = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(liker, User.class)), UserServiceModel.class);

        UserServiceModel commentCreator = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        commentCreator = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(commentCreator, User.class)), UserServiceModel.class);

        CommentServiceModel comment = this.modelMapper.map(this.entityFactory.createComment(), CommentServiceModel.class);
        comment.setCreator(commentCreator);
        comment = this.modelMapper.map(this.commentRepository.save(this.modelMapper.map(comment, Comment.class)), CommentServiceModel.class);

        boolean result = this.commentReactionServices.isLikedByUser(liker, comment);

        assertFalse(result);
    }

    @Test
    public void isLikedByUser_withUserLikedThatComment_shouldReturnTrue() {
        UserServiceModel liker = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        liker.setUsername("username2");
        liker.setEmail("email2@domain.bg");
        liker = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(liker, User.class)), UserServiceModel.class);

        UserServiceModel commentCreator = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        commentCreator = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(commentCreator, User.class)), UserServiceModel.class);

        CommentServiceModel comment = this.modelMapper.map(this.entityFactory.createComment(), CommentServiceModel.class);
        comment.setCreator(commentCreator);
        comment = this.modelMapper.map(this.commentRepository.save(this.modelMapper.map(comment, Comment.class)), CommentServiceModel.class);

        CommentReaction commentReaction = this.entityFactory.createCommentReaction();
        commentReaction.setUser(this.modelMapper.map(liker, User.class));
        commentReaction.setComment(this.modelMapper.map(comment, Comment.class));
        this.commentReactionRepository.save(commentReaction);

        boolean result = this.commentReactionServices.isLikedByUser(liker, comment);

        assertTrue(result);
    }

    @Test
    public void findTotalReactionsByUserAndType_with0Present_shouldReturn0() {
        UserServiceModel liker = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        liker.setUsername("username2");
        liker.setEmail("email2@domain.bg");
        liker = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(liker, User.class)), UserServiceModel.class);

        UserServiceModel commentCreator = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        commentCreator = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(commentCreator, User.class)), UserServiceModel.class);

        CommentServiceModel comment = this.modelMapper.map(this.entityFactory.createComment(), CommentServiceModel.class);
        comment.setCreator(commentCreator);
        comment = this.modelMapper.map(this.commentRepository.save(this.modelMapper.map(comment, Comment.class)), CommentServiceModel.class);
        int result = this.commentReactionServices.findTotalReactionsByUserAndType(liker,ReactionType.LIKE);

        assertEquals(result,0);
    }

    @Test
    public void findTotalReactionsByUserAndType_with1Present_shouldReturn1() {
        UserServiceModel liker = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        liker.setUsername("username2");
        liker.setEmail("email2@domain.bg");
        liker = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(liker, User.class)), UserServiceModel.class);

        UserServiceModel commentCreator = this.modelMapper.map(this.entityFactory.createUser(), UserServiceModel.class);
        commentCreator = this.modelMapper.map(this.userRepository.save(this.modelMapper.map(commentCreator, User.class)), UserServiceModel.class);

        CommentServiceModel comment = this.modelMapper.map(this.entityFactory.createComment(), CommentServiceModel.class);
        comment.setCreator(commentCreator);
        comment = this.modelMapper.map(this.commentRepository.save(this.modelMapper.map(comment, Comment.class)), CommentServiceModel.class);

        CommentReaction commentReaction = this.entityFactory.createCommentReaction();
        commentReaction.setUser(this.modelMapper.map(liker, User.class));
        commentReaction.setComment(this.modelMapper.map(comment, Comment.class));
        this.commentReactionRepository.save(commentReaction);

        int result = this.commentReactionServices.findTotalReactionsByUserAndType(liker,ReactionType.LIKE);

        assertEquals(result,1);
    }

}
