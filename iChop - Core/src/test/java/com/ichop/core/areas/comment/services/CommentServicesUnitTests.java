package com.ichop.core.areas.comment.services;

import com.ichop.core.areas.comment.domain.models.binding.CommentCreateBindingModel;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.exceptions.CommentNotFoundException;
import com.ichop.core.areas.comment.repositories.CommentRepository;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommentServicesUnitTests {


    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CommentRepository commentRepository;

    @Spy
    @InjectMocks
    private CommentServicesImp commentServices;


    @Before
    public void setUp() {
    }

    @Test(expected = UserNotFoundException.class)
    public void create_withNullUser_throwException() {

        CommentCreateBindingModel bindingModel = mock(CommentCreateBindingModel.class);
        ThreadServiceModel thread = mock(ThreadServiceModel.class);

        when(bindingModel.getCreator()).thenReturn(null);

        this.commentServices.create(bindingModel);
    }

    @Test(expected = ThreadNotFoundException.class)
    public void create_withNullThread_throwException() {

        CommentCreateBindingModel bindingModel = mock(CommentCreateBindingModel.class);
        UserServiceModel user = mock(UserServiceModel.class);

        when(bindingModel.getCreator()).thenReturn(user);
        when(bindingModel.getThread()).thenReturn(null);
        this.commentServices.create(bindingModel);
    }

    @Test
    public void create_withValidData_shouldInvokeMethods() {

        CommentCreateBindingModel bindingModel = mock(CommentCreateBindingModel.class);
        UserServiceModel user = mock(UserServiceModel.class);
        ThreadServiceModel thread = mock(ThreadServiceModel.class);
        CommentServiceModel comment = mock(CommentServiceModel.class);

        when(bindingModel.getThread()).thenReturn(thread);
        when(bindingModel.getCreator()).thenReturn(user);
        when(this.modelMapper.map(bindingModel, CommentServiceModel.class)).thenReturn(comment);

        this.commentServices.create(bindingModel);

        verify(comment, times(1)).setCreatedOn(any());
        verify(this.modelMapper, times(1)).map(bindingModel, CommentServiceModel.class);
        verify(this.commentServices,times(1)).save(comment,CommentServiceModel.class);
    }

    @Test(expected = UserNotFoundException.class)
    public void getTotalOfUsers_withNullUser_shouldThrowException() {
        this.commentServices.getTotalOfUser(null);
    }

    @Test
    public void getTotalOfUsers_withValidData_shouldInvokeMethods() {
        UserServiceModel user = mock(UserServiceModel.class);
        User entityUser = mock(User.class);

        when(this.modelMapper.map(user, User.class)).thenReturn(entityUser);
        this.commentServices.getTotalOfUser(user);

        verify(this.modelMapper, times(1)).map(user, User.class);
        verify(this.commentRepository, times(1)).getTotalCommentsOfUser(entityUser);
    }

    @Test
    public void findById_withNullId_shouldReturnNull() {
        assertNull(this.commentServices.findById("id"));
    }

    @Test
    public void findById_withValidData_shouldInvokeMethods() {
        this.commentServices.findById("id");

        verify(this.commentServices,times(1)).findById("id",CommentServiceModel.class);
    }

    @Test(expected = CommentNotFoundException.class)
    public void delete_withNotExistingId_shouldThrowException(){
        when(this.commentRepository.existsById("id")).thenReturn(false);

        this.commentServices.delete("id");
    }

    @Test
    public void delete_withValidData_shouldInvokeMethods(){
        when(this.commentRepository.existsById("id")).thenReturn(true);

        this.commentServices.delete("id");

        verify(this.commentServices,times(1)).deleteById("id");
    }

    @Test(expected = CommentNotFoundException.class)
    public void deleteByModel_withNotExistingModel_shouldThrowException(){
        CommentServiceModel comment = mock(CommentServiceModel.class);

        this.commentServices.deleteByModel(comment);
    }

    @Test(expected = CommentNotFoundException.class)
    public void deleteByModel_withNullModel_shouldThrowException(){
        CommentServiceModel comment = null;
        this.commentServices.deleteByModel(comment);
    }

    @Test
    public void deleteByModel_withValidModel_shouldInvokeMethods(){
        CommentServiceModel comment = mock(CommentServiceModel.class);

        when(comment.getId()).thenReturn("id");

        this.commentServices.delete(comment);

        verify(this.commentServices,times(1)).delete(comment);
    }

}
