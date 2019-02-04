package ichop.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    public ModelAndView view(String viewName, ModelAndView modelAndView) {
        modelAndView.setViewName(viewName);

        return modelAndView;
    }

    public ModelAndView view(String viewName) {
        return this.view(viewName, new ModelAndView());
    }

    public ModelAndView redirect(String redirectUrl) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:" + redirectUrl);

        return modelAndView;
    }

    public ModelAndView page(String basePage,String bodyPage,String title, ModelAndView modelAndView) {
        modelAndView.setViewName(basePage);
        modelAndView.addObject("body",bodyPage);
        modelAndView.addObject("title",title);
        return modelAndView;
    }
    public ModelAndView page(String basePage,String bodyPage,String title) {
        return this.page(basePage,bodyPage,title,new ModelAndView());
    }

    public ModelAndView viewWithMessage(String basePage,String bodyPage,String message){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(basePage);
        modelAndView.addObject("body",bodyPage);
        modelAndView.addObject("message",message);
        return modelAndView;
    }
}
