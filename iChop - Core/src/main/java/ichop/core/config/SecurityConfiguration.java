package ichop.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.role.services.UserRoleServices;
import ichop.core.areas.security.filters.JwtAuthenticationFilter;
import ichop.core.areas.security.filters.JwtAuthorizationFilter;
import ichop.core.areas.user.services.UserServices;
import ichop.core.helpers.ResponseHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserRoleServices userRoleServices;
    private final UserServices userServices;
    private final ResponseHelpers responseHelpers;
    private final ObjectMapper objectMapper;

    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    public SecurityConfiguration(UserRoleServices userRoleServices, UserServices userServices, ResponseHelpers responseHelpers, ObjectMapper objectMapper) throws Exception {
        this.userRoleServices = userRoleServices;
        this.userServices = userServices;
        this.responseHelpers = responseHelpers;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        this.jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager(), this.userRoleServices,this.responseHelpers,this.objectMapper);
        this.jwtAuthorizationFilter = new JwtAuthorizationFilter(authenticationManager(),this.objectMapper,this.responseHelpers);

        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(this.jwtAuthenticationFilter)
                .addFilter(this.jwtAuthorizationFilter)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, e) -> {
                    String json = String.format("{\"message\": \"%s\"}", e.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                });
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userServices)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

        return source;
    }
}

