package com.ichop.webstorage.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropertyConfiguration {

    /*To load multiple properties files*/
    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();

        ClassPathResource corePropertiesPath = new ClassPathResource("application.properties");
        ClassPathResource jmsPropertiesPath = new ClassPathResource("application-jms.properties");
        ClassPathResource dropboxPropertiesPath = new ClassPathResource("application-dropbox.properties");
        Resource[] resources = new ClassPathResource[]{corePropertiesPath, jmsPropertiesPath, dropboxPropertiesPath};
        ppc.setLocations(resources);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }


}
