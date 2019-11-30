package com.ichop.plugin.linkaccount.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

public class ArtemisConfiguration {

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String USERNAME = "123";
    private static final String PASSWORD = "123";

    private ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL, USERNAME, PASSWORD);
        connectionFactory.setClientID("iChop - Link Account Plugin");
        return connectionFactory;
    }

    private Connection connection() throws JMSException {
        Connection connection = this.connectionFactory().createConnection();
        connection.start();
        return connection;
    }

    public Session session() throws JMSException {
        return this.connection().createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public void initListeners() {

    }

}
