package com.ichop.plugin.linkaccount.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.*;

import static com.ichop.plugin.linkaccount.constants.LogConstants.ACTIVEMQ_INITIALIZED;

public class ArtemisConfiguration {

    private static final Logger LOG = LogManager.getLogger(ArtemisConfiguration.class);

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String CLIENT_ID = "iChop - Link Account Plugin";
    private static final String USERNAME = "123";
    private static final String PASSWORD = "123";

    private static ActiveMQConnectionFactory connectionFactory;
    private static Connection connection;
    private static Session session;

    public static ActiveMQConnectionFactory getConnectionFactory() {

        if (connectionFactory == null) {
            ActiveMQConnectionFactory f = new ActiveMQConnectionFactory(BROKER_URL, USERNAME, PASSWORD);
            f.setClientID(CLIENT_ID);
            connectionFactory = f;
            //<editor-fold desc="LOG">
            LOG.info(String.format(ACTIVEMQ_INITIALIZED, BROKER_URL, USERNAME));
            //</editor-fold>
        }

        return connectionFactory;
    }

    public static Connection getConnection() {

        if (connection == null) {
            try {
                Connection c = getConnectionFactory().createConnection();
                c.start();
                connection = c;
            } catch (Exception ex) {
                LOG.error(ex);
            }
        }

        return connection;
    }

    public static Session getSession() {

        if (session == null) {
            try {
                session = getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
            } catch (Exception ex) {
                LOG.error(ex);
            }
        }

        return session;
    }

}
