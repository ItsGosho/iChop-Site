package com.ichop.core.areas.report.services;

import com.ichop.core.areas.report.domain.models.binding.ThreadReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.ThreadReportServiceModel;
import com.ichop.core.areas.report.exceptions.ReportNotFoundException;
import com.ichop.core.areas.report.repositories.ThreadReportRepository;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
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
public class ThreadReportServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ThreadReportRepository threadReportRepository;

    @InjectMocks
    private ThreadReportServicesImp threadReportServices;


    @Before
    public void setUp() {
    }

    @Test(expected = ThreadNotFoundException.class)
    public void create_withNullThread_shouldThrowException() {
        ThreadReportCreateBindingModel bindingModel = mock(ThreadReportCreateBindingModel.class);

        when(bindingModel.getThread()).thenReturn(null);
        this.threadReportServices.create(bindingModel);
    }

    @Test(expected = UserNotFoundException.class)
    public void create_withNullUser_shouldThrowException() {
        ThreadReportCreateBindingModel bindingModel = mock(ThreadReportCreateBindingModel.class);

        when(bindingModel.getThread()).thenReturn(mock(ThreadServiceModel.class));
        when(bindingModel.getUser()).thenReturn(null);
        this.threadReportServices.create(bindingModel);
    }

    @Test
    public void create_withValidData_shouldInvokeMethods(){
        ThreadReportCreateBindingModel bindingModel = mock(ThreadReportCreateBindingModel.class);
        ThreadServiceModel thread = mock(ThreadServiceModel.class);
        UserServiceModel user = mock(UserServiceModel.class);
        ThreadReportServiceModel threadReport = mock(ThreadReportServiceModel.class);

        when(bindingModel.getThread()).thenReturn(thread);
        when(bindingModel.getUser()).thenReturn(user);
        when(this.modelMapper.map(bindingModel, ThreadReportServiceModel.class)).thenReturn(threadReport);

        this.threadReportServices.create(bindingModel);

        verify(this.modelMapper,times(1)).map(bindingModel,ThreadReportServiceModel.class);
        verify(threadReport,times(1)).setReportDate(any());
        verify(this.threadReportRepository,times(1)).save(any());
    }

    @Test(expected = ReportNotFoundException.class)
    public void deleteByModel_withNullReportPassed_shouldThrowException() {
        ThreadReportServiceModel threadReportServiceModel = mock(ThreadReportServiceModel.class);

        this.threadReportServices.deleteByModel(null);
    }

    @Test(expected = ReportNotFoundException.class)
    public void deleteByModel_withNotExistingCommentReport_shouldThrowException() {
        ThreadReportServiceModel threadReportServiceModel = mock(ThreadReportServiceModel.class);

        when(threadReportServiceModel.getId()).thenReturn("id");
        when(this.threadReportServices.existsById(threadReportServiceModel.getId())).thenReturn(false);

        this.threadReportServices.deleteByModel(threadReportServiceModel);
    }

    @Test
    public void deleteByModel_withValidData_shouldInvokeMethods() {
        ThreadReportServiceModel threadReportServiceModel = mock(ThreadReportServiceModel.class);

        when(threadReportServiceModel.getId()).thenReturn("id");
        when(this.threadReportServices.existsById(threadReportServiceModel.getId())).thenReturn(true);

        this.threadReportServices.deleteByModel(threadReportServiceModel);

        verify(this.threadReportRepository, times(1)).deleteById(threadReportServiceModel.getId());
    }

}
