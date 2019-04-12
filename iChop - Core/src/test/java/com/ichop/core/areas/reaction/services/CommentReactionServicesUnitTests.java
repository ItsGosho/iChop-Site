package com.ichop.core.areas.reaction.services;

import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.models.binding.CommentReactionCreateBindingModel;
import com.ichop.core.areas.reaction.domain.models.service.CommentReactionServiceModel;
import com.ichop.core.areas.reaction.exceptions.CantReactException;
import com.ichop.core.areas.reaction.repositories.CommentReactionRepository;
import com.ichop.core.areas.comment.exceptions.CommentNotFoundException;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserAlreadyReacted;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommentReactionServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CommentReactionRepository commentReactionRepository;

    @InjectMocks
    private CommentReactionServicesImp commentReactionServices;

    @Before
    public void setUp() {
    }

    @Test(expected = CommentNotFoundException.class)
    public void create_withNullComment_shouldThrowException() {
        CommentReactionCreateBindingModel bindingModel = mock(CommentReactionCreateBindingModel.class);

        when(bindingModel.getComment()).thenReturn(null);

        this.commentReactionServices.create(bindingModel);
    }

    @Test(expected = UserNotFoundException.class)
    public void create_withNullUser_shouldThrowException() {
        CommentReactionCreateBindingModel bindingModel = mock(CommentReactionCreateBindingModel.class);
        CommentServiceModel comment = mock(CommentServiceModel.class);

        when(bindingModel.getComment()).thenReturn(comment);
        when(bindingModel.getUser()).thenReturn(null);

        this.commentReactionServices.create(bindingModel);
    }

    @Test(expected = UserAlreadyReacted.class)
    public void create_withUserAlreadyReactedThatComment_shouldThrowException() {
        CommentReactionCreateBindingModel bindingModel = mock(CommentReactionCreateBindingModel.class);
        UserServiceModel user = mock(UserServiceModel.class);
        CommentServiceModel comment = mock(CommentServiceModel.class);

        when(bindingModel.getUser()).thenReturn(user);
        when(bindingModel.getComment()).thenReturn(comment);
        when(this.commentReactionServices.isReactedByUser(user, comment)).thenReturn(true);

        this.commentReactionServices.create(bindingModel);
    }

    @Test(expected = CantReactException.class)
    public void create_withUserTryingToReactAtHisOwnComment_shouldThrowException() {
        CommentReactionCreateBindingModel bindingModel = mock(CommentReactionCreateBindingModel.class);
        UserServiceModel user = mock(UserServiceModel.class);
        CommentServiceModel comment = mock(CommentServiceModel.class);

        when(bindingModel.getUser()).thenReturn(user);
        when(bindingModel.getComment()).thenReturn(comment);
        when(user.getUsername()).thenReturn("username");
        when(comment.getCreator()).thenReturn(user);
        when(comment.getCreator().getUsername()).thenReturn("username");
        when(this.commentReactionServices.isReactedByUser(user, comment)).thenReturn(false);

        this.commentReactionServices.create(bindingModel);
    }

    @Test
    public void create_withValidData_shouldInvokeMethods() {
        CommentReactionCreateBindingModel bindingModel = mock(CommentReactionCreateBindingModel.class);
        UserServiceModel user = mock(UserServiceModel.class);
        UserServiceModel creator = mock(UserServiceModel.class);
        CommentServiceModel comment = mock(CommentServiceModel.class);
        CommentReactionServiceModel commentReaction = mock(CommentReactionServiceModel.class);

        when(bindingModel.getUser()).thenReturn(user);
        when(bindingModel.getComment()).thenReturn(comment);
        when(user.getUsername()).thenReturn("username");
        when(comment.getCreator()).thenReturn(creator);
        when(creator.getUsername()).thenReturn("username2");
        when(this.commentReactionServices.isReactedByUser(user, comment)).thenReturn(false);
        when(this.modelMapper.map(bindingModel, CommentReactionServiceModel.class)).thenReturn(commentReaction);

        this.commentReactionServices.create(bindingModel);

        verify(this.modelMapper, times(1)).map(bindingModel, CommentReactionServiceModel.class);
        verify(commentReaction, times(1)).setReactionDate(any());
        verify(this.commentReactionRepository, times(1)).save(any());
    }

    @Test(expected = UserNotFoundException.class)
    public void isLikedByUser_withNullUser_shouldThrowException() {
        CommentServiceModel comment = mock(CommentServiceModel.class);

        this.commentReactionServices.isReactedByUser(null, comment);
    }

    @Test(expected = CommentNotFoundException.class)
    public void isLikedByUser_withNullComment_shouldThrowException() {
        UserServiceModel user = mock(UserServiceModel.class);

        this.commentReactionServices.isReactedByUser(user, null);
    }

    @Test
    public void isLikedByUser_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        CommentServiceModel comment = mock(CommentServiceModel.class);
        User entityUser = mock(User.class);
        Comment entityComment = mock(Comment.class);

        when(this.modelMapper.map(user, User.class)).thenReturn(entityUser);
        when(this.modelMapper.map(comment, Comment.class)).thenReturn(entityComment);
        this.commentReactionServices.isReactedByUser(user, comment);

        verify(this.commentReactionRepository, times(1)).isUserReactedAtThatComment(entityUser, entityComment);
    }

    @Test(expected = UserNotFoundException.class)
    public void findTotalReactionsByUserAndType_withNullUser_shouldThrowException() {
        ReactionType reactionType = ReactionType.LIKE;

        this.commentReactionServices.findTotalReactionsByUserAndType(null, reactionType);
    }

    @Test
    public void findTotalReactionsByUserAndType_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        ReactionType reactionType = ReactionType.LIKE;
        User entityUser = mock(User.class);

        when(this.modelMapper.map(user, User.class)).thenReturn(entityUser);
        this.commentReactionServices.findTotalReactionsByUserAndType(user, reactionType);

        verify(this.modelMapper,times(1)).map(user,User.class);
        verify(this.commentReactionRepository,times(1)).getUserTotalReactions(entityUser,reactionType);
    }

}
