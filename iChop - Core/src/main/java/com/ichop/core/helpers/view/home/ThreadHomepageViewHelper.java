package com.ichop.core.helpers.view.home;

import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.domain.models.view.home.ThreadHomepageViewModel;
import com.ichop.core.helpers.view.BaseViewCreator;
import com.ichop.core.service.thread.ThreadServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ThreadHomepageViewHelper extends BaseViewCreator {

    private final ThreadServices threadServices;

    @Autowired
    public ThreadHomepageViewHelper(ModelMapper modelMapper, ThreadServices threadServices) {
        super(modelMapper);
        this.threadServices = threadServices;
    }

    public Page<ThreadHomepageViewModel> create(Pageable pageable){
        Page<ThreadServiceModel> page = this.threadServices.findAllThreads(pageable);

        Page<ThreadHomepageViewModel> threads = page.map(x -> {
            ThreadHomepageViewModel threadHomepageViewModel = this.modelMapper.map(x, ThreadHomepageViewModel.class);
            threadHomepageViewModel.setTotalViews(x.getViews());
            threadHomepageViewModel.setTotalComments(x.getComments().size());
            threadHomepageViewModel.setTotalReactions(x.getReactions().size());
            return threadHomepageViewModel;
        });

        return threads;
    }

}
