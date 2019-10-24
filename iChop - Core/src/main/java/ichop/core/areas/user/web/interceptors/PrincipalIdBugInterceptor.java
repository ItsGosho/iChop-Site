package ichop.core.areas.user.web.interceptors;

import ichop.core.areas.user.domain.entities.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
*
*
* Fixes a bug where when the application is restarted the user doesn't have id,
* so this will logout him.
*
* */
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
