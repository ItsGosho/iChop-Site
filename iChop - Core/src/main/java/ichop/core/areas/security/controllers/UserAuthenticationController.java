package ichop.core.areas.security.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.security.constants.SecurityConstants;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.models.jms.register.UserRegisterRequest;
import ichop.core.areas.user.requesters.UserRequester;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@PreAuthorize("isAnonymous()")
public class UserAuthenticationController {

    private final UserRequester userRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserAuthenticationController(UserRequester userRequester, ResponseHelpers responseHelpers) {
        this.userRequester = userRequester;
        this.responseHelpers = responseHelpers;
    }

    @PostMapping(UserRoutingConstants.REGISTER)
    public ResponseEntity register(@RequestBody UserRegisterRequest request) {
        JmsReplyModel reply = this.userRequester.register(request);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PostMapping(UserRoutingConstants.LOGOUT)
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {

        boolean res = this.isJwtCookiePresent(request.getCookies());

        return null;
    }

    private boolean isJwtCookiePresent(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(SecurityConstants.JWT_COOKIE_NAME)) {
                return true;
            }
        }

        return false;
    }

}
