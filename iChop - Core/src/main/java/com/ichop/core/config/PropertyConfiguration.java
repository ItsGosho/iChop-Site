package com.ichop.core.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropertyConfiguration {

    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();

        ClassPathResource corePropertiesPath = new ClassPathResource("application.properties");
        ClassPathResource jmsPropertiesPath = new ClassPathResource("application-jms.properties");
        ClassPathResource databasePropertiesPath = new ClassPathResource("application-database.properties");
        ClassPathResource emailPropertiesPath = new ClassPathResource("application-email.properties");
        Resource[] resources = new ClassPathResource[]{corePropertiesPath, jmsPropertiesPath, databasePropertiesPath, emailPropertiesPath};
        ppc.setLocations(resources);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

}
