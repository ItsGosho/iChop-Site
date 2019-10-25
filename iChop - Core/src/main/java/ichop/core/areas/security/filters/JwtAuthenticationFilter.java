package ichop.core.areas.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import ichop.core.areas.role.services.UserRoleServices;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.constants.URLConstants;
import ichop.core.helpers.ResponseHelpers;
import ichop.core.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static ichop.core.areas.security.constants.SecurityConstants.*;
import static ichop.core.areas.security.constants.SecurityLogConstants.*;
import static ichop.core.areas.user.constants.UserResponseConstants.BAD_CREDENTIALS;
import static ichop.core.areas.user.constants.UserResponseConstants.LOGIN_SUCCESSFUL;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOG = LogManager.getLogger(JwtAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;
    private final UserRoleServices userRoleServices;
    private final ResponseHelpers responseHelpers;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserRoleServices userRoleServices, ResponseHelpers responseHelpers, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.userRoleServices = userRoleServices;
        this.responseHelpers = responseHelpers;
        this.objectMapper = objectMapper;

        super.setFilterProcessesUrl(URLConstants.USER_LOGIN);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        LOG.info(String.format(AUTHENTICATION_STARTED, request.getParameter(EMAIL_FIELD)));

        String email = request.getParameter(EMAIL_FIELD);
        String password = request.getParameter(PASSWORD_FIELD);

        try {
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            this.responseHelpers.respondSuccessful(response, LOGIN_SUCCESSFUL);
            return authentication;
        } catch (Exception ex) {
            this.responseHelpers.respondError(response, BAD_CREDENTIALS);
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain,
                                            Authentication authentication) throws IOException {
        UserServiceModel user = this.objectMapper.convertValue(authentication.getPrincipal(), UserServiceModel.class);

        LOG.info(String.format(AUTHENTICATION_SUCCESSFUL, user.getEmail()));

        UserRoleServiceModel role = this.userRoleServices.findHighestOfUser(user);
        String roles = this.objectMapper.writeValueAsString(user.getAuthorities());

        String jwt = JWT.create()
                .withExpiresAt(DateUtils.asDate(LocalDateTime.now().plusHours(JWT_EXPIRATION_HOURS)))
                .withClaim(EMAIL_CLAIM, user.getEmail())
                .withClaim(ROLE_CLAIM, role.getAuthority())
                .withClaim(ROLES_CLAIM, roles)
                .withIssuer(JWT_ISSUER)
                .sign(Algorithm.HMAC512(JWT_SECRET));


        response.addCookie(new Cookie(JWT_HEADER, jwt));

        LOG.info(String.format(JWT_GENERATION_SUCCESSFUL, user.getEmail()));
    }


}

