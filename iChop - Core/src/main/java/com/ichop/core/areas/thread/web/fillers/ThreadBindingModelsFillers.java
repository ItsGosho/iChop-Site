package com.ichop.core.areas.thread.web.fillers;

import com.ichop.core.areas.thread.domain.models.binding.ThreadCreateBindingModel;
import com.ichop.core.areas.thread.web.controllers.ThreadApiController;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.validators.method.SkipOnNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice(assignableTypes = ThreadApiController.class)
public class ThreadBindingModelsFillers {

    private final UserServices userServices;


    @Autowired
    public ThreadBindingModelsFillers(UserServices userServices) {
        this.userServices = userServices;
    }

    @SkipOnNull
    @ModelAttribute
    public ThreadCreateBindingModel fill(ThreadCreateBindingModel bindingModel,
                                         Principal principal) {
        UserServiceModel creator = this.userServices.findUserByUsername(principal.getName());

        bindingModel.setCreator(creator);

        return bindingModel;
    }

}
