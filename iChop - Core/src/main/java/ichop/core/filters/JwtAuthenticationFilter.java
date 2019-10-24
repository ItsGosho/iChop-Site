package ichop.core.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.role.domain.entities.UserRoles;
import ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import ichop.core.areas.role.services.UserRoleServices;
import ichop.core.areas.user.domain.entities.User;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.constants.URLConstants;
import ichop.core.utils.DateUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

import static ichop.core.constants.SecurityConstants.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserRoleServices userRoleServices;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserRoleServices userRoleServices) {
        this.authenticationManager = authenticationManager;
        this.userRoleServices = userRoleServices;

        super.setFilterProcessesUrl(URLConstants.USER_LOGIN);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return this.authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain,
                                            Authentication authentication) {

        /*TODO: da opravq shibanite roli*/
        User user = ((User) authentication.getPrincipal());
        UserRoleServiceModel role = this.userRoleServices.findHighestOfUser(user);
        String[] roles = (String[]) user.getAuthorities().toArray();

        String jwt = JWT.create()
                .withExpiresAt(DateUtils.asDate(LocalDateTime.now().plusHours(24)))
                .withClaim(USERNAME_CLAIM, user.getUsername())
                .withClaim(ROLE_CLAIM, role.getAuthority())
                .withArrayClaim(ROLES_CLAIM, roles)
                .withIssuer(JWT_ISSUER)
                .sign(Algorithm.HMAC512(JWT_SECRET));


        response.addHeader(JWT_HEADER, jwt);
    }
}

