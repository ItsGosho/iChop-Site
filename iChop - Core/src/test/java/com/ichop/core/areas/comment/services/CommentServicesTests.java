package com.ichop.core.areas.comment.services;

import com.ichop.core.areas.comment.domain.models.binding.CommentCreateBindingModel;
import com.ichop.core.areas.comment.repositories.CommentRepository;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class CommentServicesTests {

    private ModelMapper realModelMapper;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServicesImp commentServices;


    @Before
    public void setUp() {
        this.realModelMapper = new ModelMapper();
    }

    @Test(expected = UserNotFoundException.class)
    public void create_nullUser_throwException() {

        CommentCreateBindingModel bindingModel = Mockito.mock(CommentCreateBindingModel.class);
        ThreadServiceModel thread = Mockito.mock(ThreadServiceModel.class);

        this.commentServices.create(bindingModel, null, thread);
    }

    @Test(expected = ThreadNotFoundException.class)
    public void create_nullThread_throwException() {

        CommentCreateBindingModel bindingModel = Mockito.mock(CommentCreateBindingModel.class);
        UserServiceModel user = Mockito.mock(UserServiceModel.class);

        this.commentServices.create(bindingModel, user, null);
    }

    @Test(expected = ThreadNotFoundException.class)
    public void create_validData_shouldInvokeMethods() {

        CommentCreateBindingModel bindingModel = Mockito.mock(CommentCreateBindingModel.class);
        UserServiceModel user = Mockito.mock(UserServiceModel.class);
        ThreadServiceModel thread = Mockito.mock(ThreadServiceModel.class);

        //https://github.com/RadoslavEvgeniev/Project-Product-Shop/blob/master/app/src/test/java/org/softuni/productshop/integration/services/OrderServiceTests.java
    }

}
