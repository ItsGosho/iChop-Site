package com.ichop.core.web.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TitleInterceptor extends HandlerInterceptorAdapter {

    private static final String TITLE_PREFIX = "iChop";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if(modelAndView == null){
            return;
        }

        if(modelAndView.getModelMap() == null){
            return;
        }

        modelAndView.addObject("title", TITLE_PREFIX + "- " + modelAndView.getModelMap().get("title"));
    }
}
