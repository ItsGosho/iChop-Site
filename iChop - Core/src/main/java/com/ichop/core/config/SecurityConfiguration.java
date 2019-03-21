package com.ichop.core.config;

import com.ichop.core.handlers.UserAuthenticationSuccessfulHandler;
import com.ichop.core.handlers.UserLogoutHandler;
import com.ichop.core.service.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserServices userServices;
    private final UserAuthenticationSuccessfulHandler userAuthenticationSuccessfulHandler;
    private final UserLogoutHandler userLogoutHandler;

    @Autowired
    public SecurityConfiguration(UserServices userServices, UserAuthenticationSuccessfulHandler userAuthenticationSuccessfulHandler, UserLogoutHandler userLogoutHandler) {
        this.userServices = userServices;
        this.userAuthenticationSuccessfulHandler = userAuthenticationSuccessfulHandler;
        this.userLogoutHandler = userLogoutHandler;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/register").anonymous()
                .antMatchers("/**").permitAll()
//                .antMatchers("/css/**", "/js/**","/img/**").permitAll()
//                .antMatchers("/api/user/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureUrl("/?login=error")
                .loginPage("/login")
                .successHandler(this.userAuthenticationSuccessfulHandler)
                .usernameParameter("usernameOrEmail")
                .passwordParameter("password")
                .and()
                .logout()
                .addLogoutHandler(this.userLogoutHandler)
                .logoutSuccessUrl("/")
                .and()
                .rememberMe()
                .userDetailsService(this.userServices)
                .rememberMeParameter("rememberMe")
                .rememberMeCookieName("_nf")
                .tokenValiditySeconds(20000)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/unauthorized");
    }


//        private CsrfTokenRepository csrfTokenRepository() {
//        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//
//        repository.setSessionAttributeName("_csrf");
//
//        return repository;
//    }
}
