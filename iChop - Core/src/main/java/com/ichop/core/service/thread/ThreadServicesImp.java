package com.ichop.core.service.thread;

import com.ichop.core.domain.models.binding.thread.ThreadCreateBindingModel;
import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.repository.thread.ThreadRepository;
import com.ichop.core.service.BaseService;
import com.ichop.core.domain.entities.threads.Thread;
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
    public ThreadServiceModel createThread(ThreadCreateBindingModel threadCreateBindingModel, UserServiceModel user) {

        ThreadServiceModel thread = this.modelMapper.map(threadCreateBindingModel, ThreadServiceModel.class);
        thread.setCreatedOn(LocalDateTime.now());
        thread.setCreator(user);
        thread.setViews(0);

        ThreadServiceModel result = super.save(thread,ThreadServiceModel.class);

        return  result;
    }

    @Override
    public void increaseViews(String id) {
        super.repository.increaseViews(id);
    }

    @Override
    public List<ThreadServiceModel> findAllThreads() {
        return super.findAll(ThreadServiceModel.class);
    }

    @Override
    public Page<ThreadServiceModel> findAllThreads(Pageable pageable) {
        return super.findAll(ThreadServiceModel.class,pageable);
    }

    @Override
    public ThreadServiceModel findThreadById(String id) {
        return super.findById(id,ThreadServiceModel.class);
    }

    @Override
    public void deleteThreadById(String threadId) {
        super.deleteById(threadId);
    }

    @Override
    public boolean isThreadExistsById(String threadId) {
        return super.repository.existsById(threadId);
    }

}
