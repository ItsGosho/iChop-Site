package ichop.core.areas.thread.web.controllers;

import com.google.gson.Gson;
import ichop.core.areas.thread.domain.models.binding.ThreadCreateBindingModel;
import ichop.core.areas.thread.services.ThreadServices;
import ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ThreadApiController {

    private final ThreadServices threadServices;

    @Autowired
    public ThreadApiController(ThreadServices threadServices) {
        this.threadServices = threadServices;
    }


    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(value = URLConstants.THREAD_CREATE_POST, produces = "application/json")
    @ResponseBody
    public String proceedThreadCreation(@Valid @ModelAttribute ThreadCreateBindingModel bindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new Gson().toJson(false);
        }

        this.threadServices.create(bindingModel);

        return new Gson().toJson(true);
    }

}
