package com.ichop.core.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
public class BeansConfiguration {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

}
