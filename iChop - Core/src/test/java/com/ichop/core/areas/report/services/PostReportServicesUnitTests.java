package com.ichop.core.areas.report.services;

import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.post.exceptions.PostNotFoundException;
import com.ichop.core.areas.report.domain.models.binding.PostReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.PostReportServiceModel;
import com.ichop.core.areas.report.repositories.PostReportRepository;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostReportServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PostReportRepository postReportRepository;

    @InjectMocks
    private PostReportServicesImp postReportServices;


    @Before
    public void setUp() {
    }

    @Test(expected = PostNotFoundException.class)
    public void create_withNullPost_shouldThrowException() {
        PostReportCreateBindingModel bindingModel = mock(PostReportCreateBindingModel.class);

        when(bindingModel.getPost()).thenReturn(null);
        this.postReportServices.create(bindingModel);
    }

    @Test(expected = UserNotFoundException.class)
    public void create_withNullUser_shouldThrowException() {
        PostReportCreateBindingModel bindingModel = mock(PostReportCreateBindingModel.class);

        when(bindingModel.getPost()).thenReturn(mock(PostServiceModel.class));
        when(bindingModel.getUser()).thenReturn(null);
        this.postReportServices.create(bindingModel);
    }

    @Test
    public void create_withValidData_shouldInvokeMethods(){
        PostReportCreateBindingModel bindingModel = mock(PostReportCreateBindingModel.class);
        PostServiceModel post = mock(PostServiceModel.class);
        UserServiceModel user = mock(UserServiceModel.class);
        PostReportServiceModel postReport = mock(PostReportServiceModel.class);

        when(bindingModel.getPost()).thenReturn(post);
        when(bindingModel.getUser()).thenReturn(user);
        when(this.modelMapper.map(bindingModel, PostReportServiceModel.class)).thenReturn(postReport);

        this.postReportServices.create(bindingModel);

        verify(this.modelMapper,times(1)).map(bindingModel,PostReportServiceModel.class);
        verify(postReport,times(1)).setReportDate(any());
        verify(this.postReportRepository,times(1)).save(any());
    }
}
