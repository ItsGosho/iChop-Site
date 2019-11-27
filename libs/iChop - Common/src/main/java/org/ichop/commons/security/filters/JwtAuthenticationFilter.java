package org.ichop.commons.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ichop.commons.domain.UserReply;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.utils.DateUtils;
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
import java.util.Map;

import static org.ichop.commons.security.constants.SecurityConstants.*;
import static org.ichop.commons.security.constants.SecurityLogConstants.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOG = LogManager.getLogger(JwtAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;
    private final ResponseHelpers responseHelpers;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   ResponseHelpers responseHelpers,
                                   ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.responseHelpers = responseHelpers;
        this.objectMapper = objectMapper;

        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        LOG.info(String.format(AUTHENTICATION_STARTED, request.getParameter(EMAIL_FIELD)));

        try {
            Map<String, String> parameters = this.objectMapper.readValue(request.getInputStream(), Map.class);

            String email = parameters.get(EMAIL_FIELD);
            String password = parameters.get(PASSWORD_FIELD);

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

        UserReply user = (UserReply) authentication.getPrincipal();

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

        Cookie jwtCookie = new Cookie(JWT_COOKIE_NAME, jwt);
        jwtCookie.setSecure(false);
        jwtCookie.setHttpOnly(false);
        jwtCookie.setMaxAge(JWT_EXPIRATION_HOURS * 3600);
        jwtCookie.setPath("/");

        response.addCookie(jwtCookie);

        LOG.info(String.format(JWT_GENERATION_SUCCESSFUL, user.getEmail()));
        this.responseHelpers.respondSuccessful(response, "Login successful!",user);

    }
}

