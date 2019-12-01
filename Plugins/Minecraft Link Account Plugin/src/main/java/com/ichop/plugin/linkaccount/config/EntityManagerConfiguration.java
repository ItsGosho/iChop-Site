package com.ichop.plugin.linkaccount.config;

import com.ichop.plugin.linkaccount.database.EntityManagerCreator;
import com.ichop.plugin.linkaccount.domain.entities.Key;
import com.ichop.plugin.linkaccount.domain.entities.Link;

import javax.persistence.EntityManager;

public final class EntityManagerConfiguration {

    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static final String DOMAIN = "localhost";
    private static final String PORT = "3306";
    private static final String NAME = "ichop_link_account_plugin";

    private static EntityManager entityManager;

    public static EntityManager entityManager() {

        if (entityManager == null) {
            entityManager = new EntityManagerCreator().createEntityManager(
                            getConnectionUrl(DOMAIN, PORT, NAME),
                            USER,
                            PASSWORD,
                            Key.class, Link.class);
        }

        return entityManager;
    }

    private static String getConnectionUrl(String domain, String port, String name) {
        return "jdbc:mysql://" + domain + ":" + port + "/" + name + "?useSSL=false&amp";
    }

}