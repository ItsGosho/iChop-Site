package ichop.core.areas.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.other.utils.DateUtils;
import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.models.jms.retrieve.UserFindByEmailReply;
import ichop.core.areas.user.requester.UserRequester;
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

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOG = LogManager.getLogger(JwtAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;
    private final ResponseHelpers responseHelpers;
    private final ObjectMapper objectMapper;
    private final UserRequester userRequester;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   ResponseHelpers responseHelpers,
                                   ObjectMapper objectMapper,
                                   UserRequester userRequester) {
        this.authenticationManager = authenticationManager;
        this.responseHelpers = responseHelpers;
        this.objectMapper = objectMapper;
        this.userRequester = userRequester;

        super.setFilterProcessesUrl(UserRoutingConstants.LOGIN);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        LOG.info(String.format(AUTHENTICATION_STARTED, request.getParameter(EMAIL_FIELD)));

        String email = request.getParameter(EMAIL_FIELD);
        String password = request.getParameter(PASSWORD_FIELD);

        try {
            return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (Exception ex) {
            this.responseHelpers.respondError(response, "Bad credentials!");
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain,
                                            Authentication authentication) throws IOException {

        UserFindByEmailReply user = (UserFindByEmailReply) authentication.getPrincipal();

        LOG.info(String.format(AUTHENTICATION_SUCCESSFUL, user.getEmail()));

        String roles = this.objectMapper.writeValueAsString(user.getAuthorities());

        String jwt = JWT.create()
                .withExpiresAt(DateUtils.asDate(LocalDateTime.now().plusHours(JWT_EXPIRATION_HOURS)))
                .withClaim(EMAIL_CLAIM, user.getEmail())
                .withClaim(USERNAME_CLAIM, user.getUsername())
                .withClaim(ROLE_CLAIM, user.getAuthority())
                .withClaim(ROLES_CLAIM, roles)
                .withIssuer(JWT_ISSUER)
                .sign(Algorithm.HMAC512(JWT_SECRET));


        response.addCookie(new Cookie(JWT_COOKIE_NAME, jwt));

        LOG.info(String.format(JWT_GENERATION_SUCCESSFUL, user.getEmail()));
        this.responseHelpers.respondSuccessful(response, "Login successful!");

    }
}

