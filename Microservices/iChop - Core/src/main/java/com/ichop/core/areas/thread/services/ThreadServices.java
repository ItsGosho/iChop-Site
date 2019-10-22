package com.ichop.core.areas.thread.services;

import com.ichop.core.areas.thread.domain.models.binding.ThreadCreateBindingModel;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThreadServices {

    /*
    *
    * Creates thread.
    * @throws UserNotFoundException if the user is not found.
    * @returns ThreadServiceModel which is always valid.
    *
    * */
    ThreadServiceModel create(ThreadCreateBindingModel threadCreateBindingModel);

    /*
    *
    * Increase the current views of thread by 1
    * @ThreadNotFoundException if the thread cannot be found
    *
    * */
    void increaseViews(String id);

    /*
    *
    * Finds all threads
    *
    * */
    List<ThreadServiceModel> findAll();

    /*
     *
     * Finds all threads by pageable
     * @parameter pageable is used in the repository directly
     *
     * */
    Page<ThreadServiceModel> findAll(Pageable pageable);

    /*
    *
    * Finds a thread by her id
    * @returns ThreadServiceModel which is null if the thread is not found
    *
    * */
    ThreadServiceModel findById(String id);

    /*
    *
    * Deletes thread by her id
    * @throws ThreadNotFoundException if the thread is not found.
    *
    * */
    void delete(String threadId);

    /*
    *
    * @returns true/false if the thread is present
    *
    * */
    boolean isPresentById(String threadId);}
