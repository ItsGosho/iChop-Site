package com.ichop.core.areas.thread.helper.thread_read;

import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.domain.models.view.thread_read.CommentThreadReadViewModel;
import com.ichop.core.areas.thread.domain.models.view.thread_read.ThreadCreatorThreadReadViewModel;
import com.ichop.core.areas.thread.domain.models.view.thread_read.ThreadReadViewModel;
import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import com.ichop.core.areas.thread.helpers.view.thread_read.CommentThreadReadViewHelper;
import com.ichop.core.areas.thread.helpers.view.thread_read.ThreadCreatorThreadReadViewHelper;
import com.ichop.core.areas.thread.helpers.view.thread_read.ThreadReadViewHelper;
import com.ichop.core.areas.thread.services.ThreadServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ThreadReadViewHelperUnitTests {

    @Mock
    private ThreadServices threadServices;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CommentThreadReadViewHelper commentThreadReadViewHelper;

    @Mock
    private ThreadCreatorThreadReadViewHelper threadCreatorThreadReadViewHelper;

    @Spy
    @InjectMocks
    private ThreadReadViewHelper threadReadViewHelper;


    @Test(expected = ThreadNotFoundException.class)
    public void create_withNotExistingThread_shouldThrowException() {
        String threadId = "id";
        when(this.threadServices.isPresentById(threadId)).thenReturn(false);

        this.threadReadViewHelper.create(threadId);

    }

    @Test
    public void create_withValidData_shouldInvokeMethods() {
        String threadId = "id";
        ThreadServiceModel threadServiceModel = mock(ThreadServiceModel.class);
        ThreadCreatorThreadReadViewModel threadCreatorThreadReadViewModel = mock(ThreadCreatorThreadReadViewModel.class);
        List<CommentThreadReadViewModel> comments = mock(List.class);
        ThreadReadViewModel threadReadViewModel = mock(ThreadReadViewModel.class);

        when(threadServiceModel.getViews()).thenReturn(0);
        when(this.threadServices.isPresentById(threadId)).thenReturn(true);
        when(this.threadServices.findById(threadId)).thenReturn(threadServiceModel);
        when(this.threadCreatorThreadReadViewHelper.create(threadId)).thenReturn(threadCreatorThreadReadViewModel);
        when(this.commentThreadReadViewHelper.create(threadId)).thenReturn(comments);
        when(this.modelMapper.map(threadServiceModel, ThreadReadViewModel.class)).thenReturn(threadReadViewModel);

        this.threadReadViewHelper.create(threadId);

        verify(threadReadViewModel,times(1)).setTotalViews(0);
        verify(threadReadViewModel,times(1)).setTotalComments(0);
        verify(threadReadViewModel,times(1)).setCreator(threadCreatorThreadReadViewModel);
        verify(threadReadViewModel,times(1)).setComments(comments);
        verify(this.threadServices,times(1)).isPresentById(threadId);
        verify(this.threadServices,times(1)).findById(threadId);
        verify(this.threadCreatorThreadReadViewHelper,times(1)).create(threadId);
        verify(this.commentThreadReadViewHelper,times(1)).create(threadId);
        verify(this.modelMapper,times(1)).map(threadServiceModel,ThreadReadViewModel.class);

    }

}
