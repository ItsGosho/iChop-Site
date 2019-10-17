package com.ichop.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfiguration {

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username(this.username)
                .password(this.password)
                .url(this.url)
                .driverClassName(this.driverClassName)
                .build();
    }

    @Bean
    @Primary
    public HibernateProperties hibernateProperties() {
        HibernateProperties hibernateProperties = new HibernateProperties();
        hibernateProperties.setDdlAuto(this.ddlAuto);

        return hibernateProperties;
    }

    @Bean
    @Primary
    public JpaProperties jpaProperties(){
        JpaProperties jpaProperties = new JpaProperties();
        Map<String,String> properties = new HashMap<>();
        properties.put("hibernate.dialect",dialect);
        jpaProperties.setProperties(properties);

        return jpaProperties;
    }

}
