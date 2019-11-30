package com.ichop.plugin.linkaccount.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;

public class ArtemisConfiguration {

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String CLIENT_ID = "iChop - Link Account Plugin";
    private static final String USERNAME = "123";
    private static final String PASSWORD = "123";

    private static ActiveMQConnectionFactory connectionFactory;
    private static Connection connection;
    private static Session session;

    private static ActiveMQConnectionFactory connectionFactory() {

        if (connectionFactory == null) {
            ActiveMQConnectionFactory f = new ActiveMQConnectionFactory(BROKER_URL, USERNAME, PASSWORD);
            f.setClientID(CLIENT_ID);
            connectionFactory = f;
        }

        return connectionFactory;
    }

    private static Connection connection() throws JMSException {

        if (connection == null) {
            Connection c = connectionFactory().createConnection();
            c.start();
            connection = c;
        }

        return connection;
    }

    public static Session session() throws JMSException {

        if (session == null) {
            session = connection().createSession(false, Session.AUTO_ACKNOWLEDGE);
        }

        return session;
    }

}
