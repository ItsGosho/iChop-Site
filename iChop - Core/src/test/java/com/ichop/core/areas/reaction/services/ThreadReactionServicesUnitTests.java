package com.ichop.core.areas.reaction.services;

import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.models.binding.ThreadReactionCreateBindingModel;
import com.ichop.core.areas.reaction.domain.models.service.ThreadReactionServiceModel;
import com.ichop.core.areas.reaction.repositories.ThreadReactionRepository;
import com.ichop.core.areas.thread.domain.entities.Thread;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
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
public class ThreadReactionServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ThreadReactionRepository threadReactionRepository;

    @InjectMocks
    private ThreadReactionServicesImp threadReactionServices;

    @Before
    public void setUp() {
    }

    @Test(expected = UserNotFoundException.class)
    public void create_withNullUser_shouldThrowException() {
        ThreadReactionCreateBindingModel bindingModel = mock(ThreadReactionCreateBindingModel.class);

        when(bindingModel.getUser()).thenReturn(null);

        this.threadReactionServices.create(bindingModel);
    }

    @Test(expected = ThreadNotFoundException.class)
    public void create_withNullThread_shouldThrowException() {
        ThreadReactionCreateBindingModel bindingModel = mock(ThreadReactionCreateBindingModel.class);

        when(bindingModel.getThread()).thenReturn(null);
        when(bindingModel.getUser()).thenReturn(mock(UserServiceModel.class));

        this.threadReactionServices.create(bindingModel);
    }

    @Test(expected = UserAlreadyReacted.class)
    public void create_withUserAlreadyReactedAtThisThread_shouldThrowException() {
        ThreadReactionCreateBindingModel bindingModel = mock(ThreadReactionCreateBindingModel.class);
        UserServiceModel user = mock(UserServiceModel.class);
        ThreadServiceModel thread = mock(ThreadServiceModel.class);

        when(bindingModel.getThread()).thenReturn(thread);
        when(bindingModel.getUser()).thenReturn(user);
        when(this.threadReactionServices.isLikedByUser(user,thread)).thenReturn(true);

        this.threadReactionServices.create(bindingModel);
    }

    @Test
    public void create_withValidData_shouldInvokeMethods() {
        ThreadReactionCreateBindingModel bindingModel = mock(ThreadReactionCreateBindingModel.class);
        UserServiceModel user = mock(UserServiceModel.class);
        ThreadServiceModel thread = mock(ThreadServiceModel.class);
        ThreadReactionServiceModel threadReaction = mock(ThreadReactionServiceModel.class);

        when(bindingModel.getThread()).thenReturn(thread);
        when(bindingModel.getUser()).thenReturn(user);
        when(this.threadReactionServices.isLikedByUser(user,thread)).thenReturn(false);
        when(this.modelMapper.map(bindingModel,ThreadReactionServiceModel.class)).thenReturn(threadReaction);

        this.threadReactionServices.create(bindingModel);

        verify(this.modelMapper,times(1)).map(bindingModel,ThreadReactionServiceModel.class);
        verify(threadReaction,times(1)).setReactionDate(any());
        verify(this.threadReactionRepository,times(1)).save(any());
    }

    @Test(expected = UserNotFoundException.class)
    public void isLikedByUser_withNullUser_shouldThrowException() {
         ThreadServiceModel thread = mock(ThreadServiceModel.class);

         this.threadReactionServices.isLikedByUser(null,thread);
    }

    @Test(expected = ThreadNotFoundException.class)
    public void isLikedByUser_withNullThread_shouldThrowException() {
        UserServiceModel user = mock(UserServiceModel.class);

        this.threadReactionServices.isLikedByUser(user,null);
    }

    @Test
    public void isLikedByUser_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        ThreadServiceModel thread = mock(ThreadServiceModel.class);
        User entityUser = mock(User.class);
        Thread entityThread = mock(Thread.class);

        when(this.modelMapper.map(user, User.class)).thenReturn(entityUser);
        when(this.modelMapper.map(thread, Thread.class)).thenReturn(entityThread);

        this.threadReactionServices.isLikedByUser(user,thread);

        verify(this.modelMapper,times(1)).map(user, User.class);
        verify(this.modelMapper,times(1)).map(thread, Thread.class);
        verify(this.threadReactionRepository,times(1)).isUserLikedThatThread(entityUser,entityThread);

    }

    @Test(expected = UserNotFoundException.class)
    public void findTotalReactionsByUserAndType_withNullUser_shouldThrowException() {
        this.threadReactionServices.findTotalReactionsByUserAndType(null, ReactionType.LIKE);
    }

    @Test
    public void findTotalReactionsByUserAndType_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        User entityUser = mock(User.class);

        when(this.modelMapper.map(user, User.class)).thenReturn(entityUser);

        this.threadReactionServices.findTotalReactionsByUserAndType(user, ReactionType.LIKE);

        verify(this.modelMapper,times(1)).map(user, User.class);
        verify(this.threadReactionRepository,times(1)).getUserTotalReactions(entityUser,ReactionType.LIKE);
    }

}
