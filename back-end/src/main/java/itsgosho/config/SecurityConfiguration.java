package itsgosho.config;

import itsgosho.service.user.UserServices;
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

    @Autowired
    public SecurityConfiguration(UserServices userServices) {
        this.userServices = userServices;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/register").anonymous()
                .antMatchers("/css/**", "/js/**").permitAll()
                //.antMatchers("/users/isvalid", "/users/exists").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .usernameParameter("usernameOrEmail")
                .passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .rememberMe()
                .userDetailsService(this.userServices)
                .rememberMeParameter("rememberMe")
                .rememberMeCookieName("_nf")
                .tokenValiditySeconds(20000)
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized");
    }
}
