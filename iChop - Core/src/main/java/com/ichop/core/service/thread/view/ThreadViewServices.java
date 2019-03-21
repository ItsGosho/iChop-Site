package com.ichop.core.service.thread.view;

import com.ichop.core.domain.models.view.home.ThreadHomepageViewModel;
import com.ichop.core.domain.models.view.thread_read.ThreadReadViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThreadViewServices {

    Page<ThreadHomepageViewModel> listAllByPage(Pageable pageable);
    ThreadReadViewModel getThreadById(String id);

}
