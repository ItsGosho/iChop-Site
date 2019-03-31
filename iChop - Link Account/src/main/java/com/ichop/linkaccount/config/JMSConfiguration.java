package com.ichop.linkaccount.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import static com.ichop.linkaccount.constants.JmsConstants.RECEIVE_TIMEOUT;

@Configuration
public class JMSConfiguration {

    @Value("${jms.brokerUrl}")
    private String brokerUrl;


    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setReceiveTimeout(RECEIVE_TIMEOUT);
        return template;
    }

}
