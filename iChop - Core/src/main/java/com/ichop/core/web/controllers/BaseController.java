package com.ichop.core.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {


    public ModelAndView view(String viewName, ModelAndView modelAndView) {
        modelAndView.setViewName(viewName);

        return modelAndView;
    }

    public ModelAndView view(String viewName) {
        return this.view(viewName, new ModelAndView());
    }

    public String redirect(String redirectUrl) {
        return "redirect:"+redirectUrl;
    }

    public ModelAndView page(String bodyPage,String title, ModelAndView modelAndView) {
        modelAndView.setViewName("base-page");
        modelAndView.addObject("body",bodyPage);
        modelAndView.addObject("title",title);
        return modelAndView;
    }
    public ModelAndView page(String bodyPage,String title) {
        return this.page(bodyPage,title,new ModelAndView());
    }

    public ModelAndView viewWithMessage(String bodyPage,String title,String message){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("base-page");
        modelAndView.addObject("title",title);
        modelAndView.addObject("body",bodyPage);
        modelAndView.addObject("message",message);
        return modelAndView;
    }
}
