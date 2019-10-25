package ichop.core.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import ichop.core.areas.role.services.UserRoleServices;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.constants.URLConstants;
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
import java.time.LocalDateTime;

import static ichop.core.constants.SecurityConstants.*;
import static ichop.core.constants.SecurityLogConstants.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOG = LogManager.getLogger(JwtAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;
    private final UserRoleServices userRoleServices;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserRoleServices userRoleServices, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.userRoleServices = userRoleServices;
        this.objectMapper = objectMapper;

        super.setFilterProcessesUrl(URLConstants.USER_LOGIN);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        LOG.info(String.format(AUTHENTICATION_STARTED,request.getParameter(USERNAME_FIELD)));

        String username = request.getParameter(USERNAME_FIELD);
        String password = request.getParameter(PASSWORD_FIELD);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return this.authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain,
                                            Authentication authentication) throws JsonProcessingException {

        LOG.info(String.format(AUTHENTICATION_SUCCESSFUL,authentication.getName()));

        UserServiceModel user = this.objectMapper.convertValue(authentication.getPrincipal(), UserServiceModel.class);
        UserRoleServiceModel role = this.userRoleServices.findHighestOfUser(user);
        String roles = this.objectMapper.writeValueAsString(user.getAuthorities());

        String jwt = JWT.create()
                .withExpiresAt(DateUtils.asDate(LocalDateTime.now().plusHours(JWT_EXPIRATION_HOURS)))
                .withClaim(USERNAME_CLAIM, user.getUsername())
                .withClaim(ROLE_CLAIM, role.getAuthority())
                .withClaim(ROLES_CLAIM, roles)
                .withIssuer(JWT_ISSUER)
                .sign(Algorithm.HMAC512(JWT_SECRET));


        response.addCookie(new Cookie(JWT_HEADER, jwt));

        LOG.info(String.format(JWT_GENERATION_SUCCESSFUL,authentication.getName()));
    }
}

