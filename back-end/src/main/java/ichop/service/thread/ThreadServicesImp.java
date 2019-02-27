package ichop.service.thread;

import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.thread.ThreadCreateBindingModel;
import ichop.domain.models.service.ThreadServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.service.thread.crud.ThreadCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ThreadServicesImp implements ThreadServices {

    private final ThreadCrudServices threadCrudServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ThreadServicesImp(ThreadCrudServices threadCrudServices, ModelMapper modelMapper) {
        this.threadCrudServices = threadCrudServices;
        this.modelMapper = modelMapper;
    }


    @Override
    public ThreadServiceModel create(ThreadCreateBindingModel threadCreateBindingModel, UserServiceModel userServiceModel) {

        User user = this.modelMapper.map(userServiceModel,User.class);

        Thread thread = this.modelMapper.map(threadCreateBindingModel, Thread.class);
        thread.setCreatedOn(LocalDateTime.now());
        thread.setCreator(user);

        this.threadCrudServices.save(this.modelMapper.map(thread,ThreadServiceModel.class));

        return this.modelMapper.map(thread,ThreadServiceModel.class);
    }

}
