package com.ichop.core.areas.report.services;

import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.report.domain.models.binding.CommentReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.CommentReportServiceModel;
import com.ichop.core.areas.report.repositories.CommentReportRepository;
import com.ichop.core.areas.thread.exceptions.CommentNotFoundException;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
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
public class CommentReportServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CommentReportRepository commentReportRepository;

    @InjectMocks
    private CommentReportServicesImp commentReportServices;


    @Before
    public void setUp() {
    }

    @Test(expected = CommentNotFoundException.class)
    public void create_withNullComment_shouldThrowException() {
        CommentReportCreateBindingModel bindingModel = mock(CommentReportCreateBindingModel.class);

        when(bindingModel.getComment()).thenReturn(null);
        this.commentReportServices.create(bindingModel);
    }

    @Test(expected = UserNotFoundException.class)
    public void create_withNullUser_shouldThrowException() {
        CommentReportCreateBindingModel bindingModel = mock(CommentReportCreateBindingModel.class);

        when(bindingModel.getComment()).thenReturn(mock(CommentServiceModel.class));
        when(bindingModel.getUser()).thenReturn(null);
        this.commentReportServices.create(bindingModel);
    }

    @Test
    public void create_withValidData_shouldInvokeMethods(){
        CommentReportCreateBindingModel bindingModel = mock(CommentReportCreateBindingModel.class);
        CommentServiceModel comment = mock(CommentServiceModel.class);
        UserServiceModel user = mock(UserServiceModel.class);
        CommentReportServiceModel commentReport = mock(CommentReportServiceModel.class);

        when(bindingModel.getComment()).thenReturn(comment);
        when(bindingModel.getUser()).thenReturn(user);
        when(this.modelMapper.map(bindingModel, CommentReportServiceModel.class)).thenReturn(commentReport);

        this.commentReportServices.create(bindingModel);

        verify(this.modelMapper,times(1)).map(bindingModel,CommentReportServiceModel.class);
        verify(commentReport,times(1)).setReportDate(any());
        verify(this.commentReportRepository,times(1)).save(any());
    }

}
