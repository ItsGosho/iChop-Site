package ichop.core.areas.thread.services;

import ichop.core.areas.thread.domain.entities.Thread;
import ichop.core.areas.thread.domain.models.binding.ThreadCreateBindingModel;
import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import ichop.core.areas.thread.repositories.ThreadRepository;
import ichop.core.areas.user.exceptions.UserNotFoundException;
import ichop.core.base.BaseService;
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
    public ThreadServiceModel create(ThreadCreateBindingModel threadCreateBindingModel) {

        if (threadCreateBindingModel.getCreator() == null) {
            throw new UserNotFoundException();
        }

        ThreadServiceModel thread = this.modelMapper.map(threadCreateBindingModel, ThreadServiceModel.class);
        thread.setCreatedOn(LocalDateTime.now());
        thread.setViews(0);

        ThreadServiceModel result = this.save(thread, ThreadServiceModel.class);

        return result;
    }

    @Override
    public void increaseViews(String id) {

        if(!this.isPresentById(id)){
            throw new ThreadNotFoundException();
        }

        this.repository.increaseViews(id);
    }

    @Override
    public List<ThreadServiceModel> findAll() {
        return this.findAll(ThreadServiceModel.class);
    }

    @Override
    public Page<ThreadServiceModel> findAll(Pageable pageable) {
        return this.findAll(ThreadServiceModel.class, pageable);
    }

    @Override
    public ThreadServiceModel findById(String id) {
        return this.findById(id, ThreadServiceModel.class);
    }

    @Override
    public void delete(String threadId) {

        if(!this.existsById(threadId)){
            throw new ThreadNotFoundException();
        }

        this.deleteById(threadId);
    }

    @Override
    public boolean isPresentById(String threadId) {
        return this.existsById(threadId);
    }

}
