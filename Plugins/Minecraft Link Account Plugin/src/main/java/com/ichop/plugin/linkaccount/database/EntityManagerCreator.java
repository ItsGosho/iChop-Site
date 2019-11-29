package com.ichop.plugin.linkaccount.database;

import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class EntityManagerCreator {

    public EntityManager createEntityManager(String dbUrl, String dbUser, String dbPassword, Class... entityClasses) {

        Properties props = new Properties();
        props.put("hibernate.connection.url", dbUrl);
        props.put("hibernate.connection.username", dbUser);
        props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        props.put("hibernate.connection.dialect", "org.hibernate.dialect.MySQL57Dialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.show_sql", "true");

        if (!dbPassword.equals("")) {
            props.put("hibernate.connection.password", dbPassword);
        }

        PersistenceUnitInfo persistenceUnitInfo = new PersistenceUnitInfo() {

            @Override
            public Properties getProperties() {
                return props;
            }

            @Override
            public List<String> getManagedClassNames() {
                return Arrays.stream(entityClasses).map(Class::getName).collect(Collectors.toList());
            }

            @Override
            public String getPersistenceUnitName() {
                return "linkaccountPU";
            }

            @Override
            public String getPersistenceProviderClassName() {
                return HibernatePersistenceProvider.class.getName();
            }

            @Override
            public PersistenceUnitTransactionType getTransactionType() {
                return null;
            }

            @Override
            public DataSource getJtaDataSource() {
                return null;
            }

            @Override
            public DataSource getNonJtaDataSource() {
                return null;
            }

            @Override
            public List<String> getMappingFileNames() {
                return null;
            }

            @Override
            public List<URL> getJarFileUrls() {
                return null;
            }

            @Override
            public URL getPersistenceUnitRootUrl() {
                return null;
            }

            @Override
            public boolean excludeUnlistedClasses() {
                return false;
            }

            @Override
            public SharedCacheMode getSharedCacheMode() {
                return null;
            }

            @Override
            public ValidationMode getValidationMode() {
                return null;
            }

            @Override
            public String getPersistenceXMLSchemaVersion() {
                return null;
            }

            @Override
            public ClassLoader getClassLoader() {
                return null;
            }

            @Override
            public void addTransformer(ClassTransformer transformer) {

            }

            @Override
            public ClassLoader getNewTempClassLoader() {
                return null;
            }
        };

        HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();

        EntityManagerFactory entityManagerFactory = hibernatePersistenceProvider
                .createContainerEntityManagerFactory(persistenceUnitInfo, Collections.EMPTY_MAP);

        return entityManagerFactory.createEntityManager();

    }
}
