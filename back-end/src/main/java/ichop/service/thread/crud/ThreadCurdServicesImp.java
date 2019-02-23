package ichop.service.thread.crud;

import ichop.domain.entities.threads.Thread;
import ichop.domain.models.service.ThreadServiceModel;
import ichop.repository.thread.ThreadRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThreadCurdServicesImp implements ThreadCrudServices {


    private final ThreadRepository threadRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ThreadCurdServicesImp(ThreadRepository threadRepository, ModelMapper modelMapper) {
        this.threadRepository = threadRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ThreadServiceModel getThread(String id) {
        Thread thread = this.threadRepository.findThreadById(id);

        if(thread != null){
            return this.modelMapper.map(thread,ThreadServiceModel.class);
        }

        return null;
    }

    @Override
    public void delete(String id) {
        Thread thread = this.threadRepository.findThreadById(id);
        this.threadRepository.delete(thread);
    }

    @Override
    public boolean exists(String id) {
        Thread thread = this.threadRepository.findThreadById(id);
        if (thread != null) {
            return true;
        }
        return false;
    }

    @Override
    public ThreadServiceModel save(ThreadServiceModel threadServiceModel) {
        Thread thread = this.modelMapper.map(threadServiceModel,Thread.class);
        this.threadRepository.save(thread);
        return this.modelMapper.map(thread,ThreadServiceModel.class);
    }

    @Override
    public List<ThreadServiceModel> findAll() {
        List<ThreadServiceModel> result =
                this.threadRepository.findAll()
                .stream()
                .map(x-> this.modelMapper.map(x,ThreadServiceModel.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public Page<Thread> findAll(Pageable pageable) {
        Page<Thread> result =
                this.threadRepository.findAll(pageable);

        return result;
    }

}
