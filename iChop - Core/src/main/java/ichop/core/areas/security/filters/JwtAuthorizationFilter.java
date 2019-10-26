package ichop.core.areas.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.requester.UserRequester;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import static ichop.core.areas.security.constants.SecurityConstants.*;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final ResponseHelpers responseHelpers;
    private final UserRequester userRequester;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  ObjectMapper objectMapper,
                                  ResponseHelpers responseHelpers,
                                  UserRequester userRequester) {

        super(authenticationManager);
        this.objectMapper = objectMapper;
        this.responseHelpers = responseHelpers;
        this.userRequester = userRequester;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        try {
            String token = Arrays.stream(request.getCookies()).filter(x -> x.getName().equals(JWT_COOKIE_NAME)).findFirst().orElse(null).getValue();

            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(JWT_SECRET)).withIssuer(JWT_ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);

            String email = jwt.getClaim(EMAIL_CLAIM).asString();
            Set<? extends GrantedAuthority> roles = this.objectMapper.readValue(jwt.getClaim(ROLES_CLAIM).asString(), new TypeReference<Set<? extends GrantedAuthority>>() {
            });

            return new UsernamePasswordAuthenticationToken(email, null, roles);
        } catch (Exception ex) {
            return null;
        }
    }
}
