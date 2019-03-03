package ichop.web.interceptors;

import ichop.domain.entities.users.User;
import ichop.service.user.crud.UserCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PrincipalIdBugInterceptor extends HandlerInterceptorAdapter {


    private final UserCrudServices userCrudServices;
    private final ModelMapper modelMapper;

    @Autowired
    public PrincipalIdBugInterceptor(UserCrudServices userCrudServices, ModelMapper modelMapper) {
        this.userCrudServices = userCrudServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object u = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(u instanceof String){
            return true;
        }

        User user = (User) u;

        if (user != null && user.getId() == null) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(null,null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        return true;
    }
}
