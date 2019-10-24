package ichop.core.areas.thread.helpers.view.thread_read;

import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import ichop.core.areas.thread.domain.models.view.thread_read.CommentThreadReadViewModel;
import ichop.core.areas.thread.domain.models.view.thread_read.ThreadCreatorThreadReadViewModel;
import ichop.core.areas.thread.domain.models.view.thread_read.ThreadReadViewModel;
import ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import ichop.core.areas.thread.services.ThreadServices;
import ichop.core.base.BaseViewCreator;
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

        if(!this.threadServices.isPresentById(threadId)){
            throw new ThreadNotFoundException();
        }

        ThreadServiceModel threadServiceModel = this.threadServices.findById(threadId);
        ThreadCreatorThreadReadViewModel creator = this.threadCreatorThreadReadViewHelper.create(threadId);
        List<CommentThreadReadViewModel> comments = this.commentThreadReadViewHelper.create(threadId);


        ThreadReadViewModel threadReadViewModel = this.modelMapper.map(threadServiceModel, ThreadReadViewModel.class);
        threadReadViewModel.setTotalViews(threadServiceModel.getViews());
        threadReadViewModel.setTotalComments(threadServiceModel.getComments().size());
        threadReadViewModel.setTotalReactions(threadServiceModel.getReactions().size());
        threadReadViewModel.setCreator(creator);
        threadReadViewModel.setComments(comments);


        return threadReadViewModel;
    }

}
