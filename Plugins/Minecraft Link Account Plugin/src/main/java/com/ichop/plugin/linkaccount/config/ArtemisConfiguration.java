package com.ichop.plugin.linkaccount.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class ArtemisConfiguration {

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String USERNAME = "123";
    private static final String PASSWORD = "123";

    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(BROKER_URL, USERNAME, PASSWORD);
    }



}
