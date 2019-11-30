package com.ichop.plugin.linkaccount.config;

import com.ichop.plugin.linkaccount.database.EntityManagerCreator;
import com.ichop.plugin.linkaccount.domain.entities.Key;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import static com.ichop.plugin.linkaccount.constants.LogConstants.DATABASE_ENTITY_MANAGER_INITIALIZED;

public final class EntityManagerConfiguration {

    private static final Logger LOG = LogManager.getLogger(EntityManagerConfiguration.class);

    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static final String DOMAIN = "localhost";
    private static final String PORT = "3306";
    private static final String NAME = "ichop_link_account_plugin";

    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {

        if (entityManager == null) {
            entityManager = new EntityManagerCreator().createEntityManager(
                    getConnectionUrl(DOMAIN, PORT, NAME),
                    USER,
                    PASSWORD,
                    Key.class);
            //<editor-fold desc="LOG">
            LOG.info(String.format(DATABASE_ENTITY_MANAGER_INITIALIZED, NAME, USER));
            //</editor-fold>
        }

        return entityManager;
    }

    private static String getConnectionUrl(String domain, String port, String name) {
        return "jdbc:mysql://" + domain + ":" + port + "/" + name + "?useSSL=false&amp;createDatabaseIfNotExist=true&amp;serverTimezone=UTC";
    }

}
