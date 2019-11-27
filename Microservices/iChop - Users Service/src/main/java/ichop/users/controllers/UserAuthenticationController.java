package ichop.users.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.constants.UserReplyConstants;
import ichop.users.constants.UserRoutingConstants;
import ichop.users.domain.models.jms.register.UserRegisterRequest;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.domain.models.view.UserViewModel;
import ichop.users.services.RoleServices;
import ichop.users.services.UserServices;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

import static org.ichop.commons.security.constants.SecurityConstants.JWT_COOKIE_NAME;

@RestController
public class UserAuthenticationController {

    private final UserServices userServices;
    private final RoleServices roleServices;
    private final ValidationHelper validationHelper;
    private final ResponseHelpers responseHelpers;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserAuthenticationController(UserServices userServices,
                                        RoleServices roleServices,
                                        ValidationHelper validationHelper,
                                        ResponseHelpers responseHelpers,
                                        ObjectMapper objectMapper) {
        this.userServices = userServices;
        this.roleServices = roleServices;
        this.validationHelper = validationHelper;
        this.responseHelpers = responseHelpers;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(UserRoutingConstants.REGISTER)
    public ResponseEntity register(@RequestBody UserRegisterRequest request) {

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        UserServiceModel user = this.userServices.register(request);

        return this.responseHelpers.respondSuccessful(UserReplyConstants.REGISTER_SUCCESSFUL);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.LOGOUT)
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie jwtCookie = new Cookie(JWT_COOKIE_NAME, null);
        jwtCookie.setSecure(false);
        jwtCookie.setHttpOnly(false);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        return this.responseHelpers.respondSuccessful(UserReplyConstants.LOGOUT_SUCCESSFUL);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(UserRoutingConstants.GET_CURRENT_AUTHENTICATED)
    public ResponseEntity getCurrentAuthenticated(Principal principal) {
        UserServiceModel user = this.userServices.findByUsername(principal.getName());

        UserViewModel viewModel = this.objectMapper.convertValue(user, UserViewModel.class);
        viewModel.setAuthority(this.roleServices.findHighestOfUser(user).getAuthority());

        return this.responseHelpers.respondSuccessful(UserReplyConstants.FETCHED_SUCCESSFUL, viewModel);
    }

}
