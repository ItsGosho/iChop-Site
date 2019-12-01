package com.ichop.basicstatistics.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseConfigurer;

import javax.sql.DataSource;

@Configuration
public class PropertyConfiguration {

    private EmbeddedDatabaseConfigurer embeddedDatabaseConfigurer;

    /*To load multiple properties files*/
    @Bean
    public static PropertyPlaceholderConfigurer properties(){
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();

        ClassPathResource corePropertiesPath = new ClassPathResource("application.properties");
        ClassPathResource jmsPropertiesPath = new ClassPathResource("application-jms.properties");
        ClassPathResource databaseProperties = new ClassPathResource("application-database.properties");
        Resource[] resources = new ClassPathResource[]{corePropertiesPath,jmsPropertiesPath,databaseProperties};
        ppc.setLocations( resources );
        ppc.setIgnoreUnresolvablePlaceholders( true );
        return ppc;
    }


}
