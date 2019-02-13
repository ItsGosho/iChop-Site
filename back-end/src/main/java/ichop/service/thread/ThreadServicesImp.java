package ichop.service.thread;

import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.thread.ThreadCreateBindingModel;
import ichop.domain.models.view.thread.ThreadHomepageViewModel;
import ichop.exceptions.user.UserException;
import ichop.exceptions.user.UserExceptionMessages;
import ichop.repository.thread.ThreadRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ThreadServicesImp implements ThreadServices {

    private final ThreadRepository threadRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ThreadServicesImp(ThreadRepository threadRepository, ModelMapper modelMapper) {
        this.threadRepository = threadRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Thread create(ThreadCreateBindingModel threadCreateBindingModel,User user){

        if(user==null) {
            throw new UserException(UserExceptionMessages.NULL);
        }

        Thread thread = this.modelMapper.map(threadCreateBindingModel,Thread.class);
        thread.setCreatedOn(LocalDateTime.now());
        thread.setCreator(user);

        this.threadRepository.save(thread);

        return thread;
    }

    @Override
    public Page<ThreadHomepageViewModel> listAllByPage(Pageable pageable) {
        Page<Thread> page = this.threadRepository.findAll(pageable);

        Page<ThreadHomepageViewModel> threads = page.map(x->{
                    ThreadHomepageViewModel threadHomepageViewModel = this.modelMapper.map(x,ThreadHomepageViewModel.class);
                    threadHomepageViewModel.setTotalViews(x.getViews());
                    threadHomepageViewModel.setTotalComments(x.getComments().size());
                    threadHomepageViewModel.setTotalReactions(x.getReacts().size());

                    return threadHomepageViewModel;
                });
        return threads;
    }

    @Override
    public void delete(String id) {
        Thread thread = this.threadRepository.findThreadById(id);
        this.threadRepository.delete(thread);
    }

    @Override
    public boolean exists(String id) {
        Thread thread = this.threadRepository.findThreadById(id);
        if(thread!=null){
            return true;
        }
        return false;
    }


}
