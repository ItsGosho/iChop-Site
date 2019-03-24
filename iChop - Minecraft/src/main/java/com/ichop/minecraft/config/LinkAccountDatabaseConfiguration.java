package com.ichop.minecraft.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "playerLinkAccountEntityManager",
        transactionManagerRef = "playerLinkAccountTransactionManager",
        basePackages = "com.ichop.minecraft.linkaccount.repository"
)
public class LinkAccountDatabaseConfiguration {

    private final Environment env;

    @Autowired
    public LinkAccountDatabaseConfiguration(Environment env) {
        this.env = env;
    }


    @Bean
    public DataSource playerLinkAccountDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.env.getProperty("player.link.account.datasource.driver-class-name"));
        dataSource.setUrl(this.env.getProperty("player.link.account.datasource.url"));
        dataSource.setUsername(this.env.getProperty("player.link.account.datasource.username"));
        dataSource.setPassword(this.env.getProperty("player.link.account.datasource.password"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.ichop.minecraft.linkaccount.domain.entities");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        em.setJpaProperties(properties);

        return em;
    }

    @Bean(name = "playerLinkAccountEntityManager")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(playerLinkAccountDataSource())
                .properties(hibernateProperties())
                .packages("com.ichop.minecraft.linkaccount.domain.entities")
                .persistenceUnit("playerLinkAccountPU")
                .build();
    }

    @Bean(name = "playerLinkAccountTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("playerLinkAccountEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Map<String, Object> hibernateProperties() {

        Resource resource = new ClassPathResource("hibernate.properties");
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            return properties.entrySet().stream()
                    .collect(Collectors.toMap(
                            e -> e.getKey().toString(),
                            Map.Entry::getValue)
                    );
        } catch (IOException e) {
            return new HashMap<String, Object>();
        }
    }
}