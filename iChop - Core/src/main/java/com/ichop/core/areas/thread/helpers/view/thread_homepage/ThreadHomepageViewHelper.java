package com.ichop.core.areas.thread.helpers.view.thread_homepage;

import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.domain.models.view.thread_homepage.ThreadHomepageViewModel;
import com.ichop.core.areas.thread.services.ThreadServices;
import com.ichop.core.base.BaseViewCreator;
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
        Page<ThreadServiceModel> page = this.threadServices.findAll(pageable);

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
