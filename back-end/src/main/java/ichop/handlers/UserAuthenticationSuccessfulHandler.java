package ichop.handlers;

import ichop.domain.entities.users.User;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.service.user.crud.UserCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class UserAuthenticationSuccessfulHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final UserCrudServices userCrudServices;
    private final ModelMapper modelMapper;

    @Autowired
    public UserAuthenticationSuccessfulHandler(UserCrudServices userCrudServices, ModelMapper modelMapper) {
        this.userCrudServices = userCrudServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication) throws IOException, ServletException {

        UserServiceModel userServiceModel = this.modelMapper.map(authentication.getPrincipal(), UserServiceModel.class);
        this.userCrudServices.updateLastOnline(userServiceModel, LocalDateTime.now());

        redirectStrategy.sendRedirect(arg0, arg1, "/");
    }
}
