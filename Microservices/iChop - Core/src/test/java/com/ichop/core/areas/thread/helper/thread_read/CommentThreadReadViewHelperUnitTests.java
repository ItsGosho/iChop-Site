package com.ichop.core.areas.thread.helper.thread_read;

import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.domain.models.view.thread_read.CommentCreatorThreadReadViewModel;
import com.ichop.core.areas.thread.domain.models.view.thread_read.CommentThreadReadViewModel;
import com.ichop.core.areas.thread.helpers.view.thread_read.CommentCreatorThreadReadViewHelper;
import com.ichop.core.areas.thread.helpers.view.thread_read.CommentThreadReadViewHelper;
import com.ichop.core.areas.thread.services.ThreadServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommentThreadReadViewHelperUnitTests {

    @Mock
    private ThreadServices threadServices;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CommentCreatorThreadReadViewHelper commentCreatorThreadReadViewHelper;

    @Spy
    @InjectMocks
    private CommentThreadReadViewHelper commentThreadReadViewHelper;


    @Test
    public void create_withValidData_shouldInvokeMethods(){
        String threadId = "threadId";
        ThreadServiceModel thread = mock(ThreadServiceModel.class);
        CommentServiceModel comment = mock(CommentServiceModel.class);
        CommentThreadReadViewModel commentThreadReadViewModel = mock(CommentThreadReadViewModel.class);
        CommentCreatorThreadReadViewModel commentCreatorThreadReadViewModel = mock(CommentCreatorThreadReadViewModel.class);

        when(thread.getComments()).thenReturn(Arrays.asList(comment));
        when(comment.getId()).thenReturn("id");
        when(this.threadServices.findById(threadId)).thenReturn(thread);
        when(this.modelMapper.map(comment,CommentThreadReadViewModel.class)).thenReturn(commentThreadReadViewModel);
        when(this.commentCreatorThreadReadViewHelper.create(comment.getId())).thenReturn(commentCreatorThreadReadViewModel);

        this.commentThreadReadViewHelper.create(threadId);

        verify(commentThreadReadViewModel,times(1)).setTotalLikes(0L);
        verify(commentThreadReadViewModel,times(1)).setTotalDislikes(0L);
        verify(commentThreadReadViewModel,times(1)).setCommentCreator(commentCreatorThreadReadViewModel);
    }

}
