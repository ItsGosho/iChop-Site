package itsgosho.service.thread;

import itsgosho.domain.entities.threads.Thread;
import itsgosho.domain.entities.users.User;
import itsgosho.domain.models.binding.thread.ThreadCreateBindingModel;
import itsgosho.domain.models.view.thread.ThreadHomepageViewModel;
import itsgosho.exceptions.thread.ThreadCannotBeNullException;
import itsgosho.exceptions.thread.ThreadNotFoundException;
import itsgosho.exceptions.user.UserCannotBeNullException;
import itsgosho.exceptions.user.UserNotFoundException;
import itsgosho.repository.thread.ThreadRepository;
import itsgosho.service.user.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ThreadServicesImp implements ThreadServices {

    private final ThreadRepository threadRepository;
    private final UserServices userServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ThreadServicesImp(ThreadRepository threadRepository, UserServices userServices, ModelMapper modelMapper) {
        this.threadRepository = threadRepository;
        this.userServices = userServices;
        this.modelMapper = modelMapper;
    }


    @Override
    public void create(ThreadCreateBindingModel threadCreateBindingModel,String creatorUsername){

        User user = this.userServices.getUserByUsername(creatorUsername);

        if(user==null){
            throw new UserNotFoundException();
        }

        Thread thread = this.modelMapper.map(threadCreateBindingModel,Thread.class);
        thread.setCreatedOn(LocalDateTime.now());
        thread.setCreator(user);

        this.threadRepository.save(thread);
    }

    @Override
    public Page<ThreadHomepageViewModel> listAllByPage(Pageable pageable) {
        Page<ThreadHomepageViewModel> threads = this.threadRepository
                .findAll(pageable)
                .map(x->{
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
        if(!this.exists(id)){
            throw new ThreadNotFoundException();
        }
        Thread thread = this.threadRepository.findById(id).orElse(null);
        this.threadRepository.delete(thread);
    }

    @Override
    public boolean exists(String id) {
        Thread thread = this.threadRepository.findById(id).orElse(null);
        if(thread!=null){
            return true;
        }
        return false;
    }


}
