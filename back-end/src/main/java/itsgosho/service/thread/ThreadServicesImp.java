package itsgosho.service.thread;

import itsgosho.domain.entities.threads.Thread;
import itsgosho.domain.entities.users.User;
import itsgosho.domain.models.binding.thread.ThreadCreateBindingModel;
import itsgosho.exceptions.user.UserCannotBeNullException;
import itsgosho.exceptions.user.UserNotFoundException;
import itsgosho.repository.thread.ThreadRepository;
import itsgosho.service.user.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public void createThread(ThreadCreateBindingModel threadCreateBindingModel,String creatorUsername){

        User user = this.userServices.getUserByUsername(creatorUsername);

        if(user==null){
            throw new UserNotFoundException();
        }

        Thread thread = this.modelMapper.map(threadCreateBindingModel,Thread.class);
        thread.setCreatedOn(LocalDateTime.now());
        thread.setCreator(user);

        this.threadRepository.save(thread);
    }
}
