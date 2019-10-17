package com.ichop.core.config;

import com.ichop.core.web.interceptors.FaviconInterceptor;
import com.ichop.core.web.interceptors.TitleInterceptor;
import com.ichop.core.areas.user.web.interceptors.PrincipalIdBugInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorsConfiguration extends WebMvcConfigurerAdapter {

    private final PrincipalIdBugInterceptor principalIdBugInterceptor;
    private final TitleInterceptor titleInterceptor;
    private final FaviconInterceptor faviconInterceptor;

    @Autowired
    public InterceptorsConfiguration(PrincipalIdBugInterceptor principalIdBugInterceptor, TitleInterceptor titleInterceptor, FaviconInterceptor faviconInterceptor) {
        this.principalIdBugInterceptor = principalIdBugInterceptor;
        this.titleInterceptor = titleInterceptor;
        this.faviconInterceptor = faviconInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.principalIdBugInterceptor);
        registry.addInterceptor(this.titleInterceptor);
        registry.addInterceptor(this.faviconInterceptor);
    }
}
