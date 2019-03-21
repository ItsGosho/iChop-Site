package com.ichop.core.service.thread;

import com.ichop.core.domain.models.binding.thread.ThreadCreateBindingModel;
import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThreadServices {

    ThreadServiceModel createThread(ThreadCreateBindingModel threadCreateBindingModel, UserServiceModel user);

    void increaseViews(String id);

    List<ThreadServiceModel> findAllThreads();
    Page<ThreadServiceModel> findAllThreads(Pageable pageable);
    ThreadServiceModel findThreadById(String id);

    void deleteThreadById(String threadId);

    boolean isThreadExistsById(String threadId);}
