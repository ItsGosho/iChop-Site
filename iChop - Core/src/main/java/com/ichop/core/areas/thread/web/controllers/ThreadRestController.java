package com.ichop.core.areas.thread.web.controllers;

import com.google.gson.Gson;
import com.ichop.core.areas.thread.domain.models.binding.ThreadCreateBindingModel;
import com.ichop.core.areas.thread.services.ThreadServices;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.constants.URLConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public String proceedThreadCreation(@Valid ThreadCreateBindingModel threadCreateBindingModel, BindingResult bindingResult, Principal principal) {

        if(bindingResult.hasErrors()){
             return new Gson().toJson(false);
        }

        User user = (User) ((Authentication) principal).getPrincipal();
        threadCreateBindingModel.setCreator(this.modelMapper.map(user, UserServiceModel.class));
        this.threadServices.create(threadCreateBindingModel);

        return new Gson().toJson(true);
    }

}
