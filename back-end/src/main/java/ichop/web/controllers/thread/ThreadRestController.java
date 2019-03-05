package ichop.web.controllers.thread;

import com.google.gson.Gson;
import ichop.constants.URLConstants;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.thread.ThreadCreateBindingModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.service.threads.thread.ThreadServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class ThreadRestController {

    private final ThreadServices threadServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ThreadRestController(ThreadServices threadServices, ModelMapper modelMapper) {
        this.threadServices = threadServices;
        this.modelMapper = modelMapper;
    }


    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(value = URLConstants.THREAD_CREATE_POST,produces = "application/json")
    @ResponseBody
    public String threadCreate(@Valid ThreadCreateBindingModel threadCreateBindingModel
            , BindingResult bindingResult
            , Principal principal) {

        if(bindingResult.hasErrors()){
             return new Gson().toJson(false);
        }
        User user = (User) ((Authentication) principal).getPrincipal();

        this.threadServices.create(threadCreateBindingModel, this.modelMapper.map(user, UserServiceModel.class));
        return new Gson().toJson(true);
    }

}
