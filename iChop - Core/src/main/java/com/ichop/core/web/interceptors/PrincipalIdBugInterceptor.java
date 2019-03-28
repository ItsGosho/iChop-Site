package com.ichop.core.web.interceptors;

import com.ichop.core.domain.entities.users.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PrincipalIdBugInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object u = SecurityContextHolder.getContext().getAuthentication() != null ?
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;

        if(u == null){
            return true;
        }

        if(u instanceof String){
            return true;
        }

        User user = (User) u;

        if (user.getId() == null) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(null,null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        return true;
    }
}
