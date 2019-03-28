package com.ichop.core.helpers.view.thread_read;

import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.domain.models.view.thread_read.CommentThreadReadViewModel;
import com.ichop.core.domain.models.view.thread_read.ThreadCreatorThreadReadViewModel;
import com.ichop.core.domain.models.view.thread_read.ThreadReadViewModel;
import com.ichop.core.helpers.view.BaseViewCreator;
import com.ichop.core.service.thread.ThreadServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ThreadReadViewHelper extends BaseViewCreator {

    private final ThreadServices threadServices;
    private final CommentThreadReadViewHelper commentThreadReadViewHelper;
    private final ThreadCreatorThreadReadViewHelper threadCreatorThreadReadViewHelper;

    @Autowired
    public ThreadReadViewHelper(ModelMapper modelMapper, ThreadServices threadServices, CommentThreadReadViewHelper commentThreadReadViewHelper, ThreadCreatorThreadReadViewHelper threadCreatorThreadReadViewHelper) {
        super(modelMapper);
        this.threadServices = threadServices;
        this.commentThreadReadViewHelper = commentThreadReadViewHelper;
        this.threadCreatorThreadReadViewHelper = threadCreatorThreadReadViewHelper;
    }


    public ThreadReadViewModel create(String threadId) {
        ThreadServiceModel threadServiceModel = this.threadServices.findThreadById(threadId);
        ThreadCreatorThreadReadViewModel creator = this.threadCreatorThreadReadViewHelper.create(threadId);
        List<CommentThreadReadViewModel> comments = this.commentThreadReadViewHelper.create(threadId);


        ThreadReadViewModel threadReadViewModel = super.modelMapper.map(threadServiceModel, ThreadReadViewModel.class);
        threadReadViewModel.setTotalViews(threadServiceModel.getViews());
        threadReadViewModel.setTotalComments(threadServiceModel.getComments().size());
        threadReadViewModel.setTotalReactions(threadServiceModel.getReactions().size());
        threadReadViewModel.setCreator(creator);
        threadReadViewModel.setComments(comments);


        return threadReadViewModel;
    }

}
