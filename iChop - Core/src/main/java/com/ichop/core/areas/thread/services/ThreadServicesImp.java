package com.ichop.core.areas.thread.services;

import com.ichop.core.areas.thread.domain.entities.Thread;
import com.ichop.core.areas.thread.domain.models.binding.ThreadCreateBindingModel;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import com.ichop.core.areas.thread.repositories.ThreadRepository;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.base.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ThreadServicesImp extends BaseService<Thread, ThreadRepository> implements ThreadServices {


    @Autowired
    public ThreadServicesImp(ModelMapper modelMapper, ThreadRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public ThreadServiceModel create(ThreadCreateBindingModel threadCreateBindingModel, UserServiceModel user) {

        if (user == null) {
            throw new UserNotFoundException();
        }

        ThreadServiceModel thread = this.modelMapper.map(threadCreateBindingModel, ThreadServiceModel.class);
        thread.setCreatedOn(LocalDateTime.now());
        thread.setCreator(user);
        thread.setViews(0);

        ThreadServiceModel result = super.save(thread, ThreadServiceModel.class);

        return result;
    }

    @Override
    public void increaseViews(String id) {
        super.repository.increaseViews(id);
    }

    @Override
    public List<ThreadServiceModel> findAll() {
        return super.findAll(ThreadServiceModel.class);
    }

    @Override
    public Page<ThreadServiceModel> findAll(Pageable pageable) {
        return super.findAll(ThreadServiceModel.class, pageable);
    }

    @Override
    public ThreadServiceModel findById(String id) {
        return super.findById(id, ThreadServiceModel.class);
    }

    @Override
    public void deleteById(String threadId) {

        if(!super.existsById(threadId)){
            throw new ThreadNotFoundException();
        }

        super.deleteById(threadId);
    }

    @Override
    public boolean isPresentById(String threadId) {
        return super.repository.existsById(threadId);
    }

}
