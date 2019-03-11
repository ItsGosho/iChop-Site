package ichop.service.thread;

import ichop.domain.models.binding.thread.ThreadCreateBindingModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
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
    public ThreadServiceModel create(ThreadCreateBindingModel threadCreateBindingModel, UserServiceModel user) {

        ThreadServiceModel thread = this.modelMapper.map(threadCreateBindingModel, ThreadServiceModel.class);
        thread.setCreatedOn(LocalDateTime.now());
        thread.setCreator(user);
        thread.setViews(0);

        ThreadServiceModel result = this.threadCrudServices.save(thread);

        return  result;
    }

}
