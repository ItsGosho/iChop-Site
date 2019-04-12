package com.ichop.core.areas.post.web.fillers;

import com.ichop.core.areas.post.domain.models.binding.PostCreateBindingModel;
import com.ichop.core.areas.post.web.controllers.PostController;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.areas.jms.validations.SkipOnNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@ControllerAdvice(assignableTypes = PostController.class)
public class PostBindingModelsFillers {

    private final UserServices userServices;

    @Autowired
    public PostBindingModelsFillers(UserServices userServices) {
        this.userServices = userServices;
    }

    @SkipOnNull
    @ModelAttribute
    public PostCreateBindingModel fill(PostCreateBindingModel bindingModel, @PathVariable(required = false) String userUsername, Principal principal) {
        UserServiceModel user = this.userServices.findUserByUsername(userUsername);
        UserServiceModel creator = this.userServices.findUserByUsername(principal.getName());

        bindingModel.setUser(user);
        bindingModel.setCreator(creator);

        return bindingModel;
    }

}
