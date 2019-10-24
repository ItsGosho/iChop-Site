package ichop.core.areas.user.handlers;

import ichop.core.areas.role.services.UserRoleServices;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.areas.user.services.UserServices;
import ichop.core.constants.URLConstants;
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

    private final UserServices userServices;
    private final UserRoleServices userRoleServices;
    private final ModelMapper modelMapper;

    @Autowired
    public UserAuthenticationSuccessfulHandler(UserServices userServices, UserRoleServices userRoleServices, ModelMapper modelMapper) {
        this.userServices = userServices;
        this.userRoleServices = userRoleServices;
        this.modelMapper = modelMapper;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserServiceModel userServiceModel = this.modelMapper.map(authentication.getPrincipal(), UserServiceModel.class);
        this.userServices.updateLastOnline(userServiceModel, LocalDateTime.now());
        this.userServices.updateUserLocation(userServiceModel,request.getParameter("userLocation"));

        request.getSession().setAttribute("user-role",this.userRoleServices.findHighestOfUser(userServiceModel).getAuthority());

        redirectStrategy.sendRedirect(request, response, URLConstants.HOME_GET);
    }
}
