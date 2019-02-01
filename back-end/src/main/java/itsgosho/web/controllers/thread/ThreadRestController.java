package itsgosho.web.controllers.thread;

import com.google.gson.Gson;
import itsgosho.constants.URLConstants;
import itsgosho.domain.models.binding.thread.ThreadCreateBindingModel;
import itsgosho.service.thread.ThreadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class ThreadRestController {

    private final ThreadServices threadServices;

    @Autowired
    public ThreadRestController(ThreadServices threadServices) {
        this.threadServices = threadServices;
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

        this.threadServices.create(threadCreateBindingModel,principal.getName());
        return new Gson().toJson(true);
    }
}
