package ichop.config;

import ichop.web.interceptors.PrincipalIdBugInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorsConfiguration extends WebMvcConfigurerAdapter {

    private final PrincipalIdBugInterceptor principalIdBugInterceptor;

    @Autowired
    public InterceptorsConfiguration(PrincipalIdBugInterceptor principalIdBugInterceptor) {
        this.principalIdBugInterceptor = principalIdBugInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.principalIdBugInterceptor);
    }
}
