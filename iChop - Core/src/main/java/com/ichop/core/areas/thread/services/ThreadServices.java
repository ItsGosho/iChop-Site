package com.ichop.core.areas.thread.services;

import com.ichop.core.areas.thread.domain.models.binding.ThreadCreateBindingModel;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThreadServices {

    ThreadServiceModel create(ThreadCreateBindingModel threadCreateBindingModel);

    void increaseViews(String id);

    List<ThreadServiceModel> findAll();
    Page<ThreadServiceModel> findAll(Pageable pageable);
    ThreadServiceModel findById(String id);

    void delete(String threadId);

    boolean isPresentById(String threadId);}
