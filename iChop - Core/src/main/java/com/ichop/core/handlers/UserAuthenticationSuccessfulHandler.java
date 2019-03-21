package com.ichop.core.handlers;

import com.ichop.core.constants.URLConstants;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.service.user.UserServices;
import com.ichop.core.service.role.UserRoleServices;
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

        request.getSession().setAttribute("user-role",this.userRoleServices.findHighestRoleOfUser(userServiceModel).getAuthority());

        redirectStrategy.sendRedirect(request, response, URLConstants.HOME_GET);
    }
}
