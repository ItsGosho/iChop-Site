package com.ichop.core.areas.thread.services;

import com.ichop.core.areas.thread.domain.models.binding.ThreadCreateBindingModel;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import com.ichop.core.areas.thread.repositories.ThreadRepository;
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
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ThreadServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ThreadRepository threadRepository;

    @Spy
    @InjectMocks
    private ThreadServicesImp threadServices;


    @Before
    public void setUp() {
    }

    @Test(expected = UserNotFoundException.class)
    public void create_withNullUser_shouldThrowException() {
        ThreadCreateBindingModel bindingModel = mock(ThreadCreateBindingModel.class);

        when(bindingModel.getCreator()).thenReturn(null);
        this.threadServices.create(bindingModel);
    }

    @Test
    public void create_withValidParameters_shouldInvokeMethods() {
        ThreadCreateBindingModel bindingModel = mock(ThreadCreateBindingModel.class);
        ThreadServiceModel thread = mock(ThreadServiceModel.class);

        when(bindingModel.getCreator()).thenReturn(mock(UserServiceModel.class));
        when(this.modelMapper.map(bindingModel,ThreadServiceModel.class)).thenReturn(thread);
        this.threadServices.create(bindingModel);

        verify(this.modelMapper,times(1)).map(bindingModel,ThreadServiceModel.class);
        verify(thread,times(1)).setCreatedOn(any());
        verify(thread,times(1)).setViews(0);
        verify(this.threadServices,times(1)).save(thread,ThreadServiceModel.class);
    }

    @Test(expected = ThreadNotFoundException.class)
    public void increaseViews_withNotValidThread_shouldThrowException(){
        when(this.threadServices.isPresentById("id")).thenReturn(false);
        this.threadServices.increaseViews("id");
    }

    @Test
    public void increaseViews_withValidData_shouldInvokeMethods(){
        when(this.threadServices.isPresentById("id")).thenReturn(true);
        this.threadServices.increaseViews("id");

        verify(this.threadServices,times(1)).increaseViews("id");
    }

    @Test
    public void finalAll_shouldInvokeMethods(){
        this.threadServices.findAll();

        verify(this.threadServices,times(1)).findAll(ThreadServiceModel.class);
    }

    @Test
    public void finalAll_withValidPageableParameter_shouldInvokeMethods(){
        Pageable pageable = mock(Pageable.class);

        doReturn(null).when(this.threadServices).findAll(ThreadServiceModel.class,pageable);

        this.threadServices.findAll(pageable);

        verify(this.threadServices,times(1)).findAll(ThreadServiceModel.class,pageable);
    }

    @Test
    public void findById_withValidId_shouldInvokeMethods(){
        this.threadServices.findById("id");

        verify(this.threadServices,times(1)).findById("id",ThreadServiceModel.class);
    }

    @Test(expected = ThreadNotFoundException.class)
    public void deleteById_withNotExistingId_shouldThrowException(){

        when(this.threadServices.existsById("id")).thenReturn(false);

        this.threadServices.delete("id");
    }

    @Test
    public void deleteById_withExistingId_shouldInvokeMethods(){

        when(this.threadServices.existsById("id")).thenReturn(true);

        this.threadServices.delete("id");

        verify(this.threadServices,times(1)).delete("id");
    }

    @Test
    public void isPresentById_shouldInvokeMethods(){
        this.threadServices.isPresentById("id");

        verify(this.threadServices,times(1)).isPresentById("id");
    }

}
