package com.ichop.linkaccount.config;

import com.ichop.linkaccount.handlers.JmsErrorHandler;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import static com.ichop.linkaccount.constants.JmsConstants.RECEIVE_TIMEOUT;

@Configuration
public class JMSConfiguration {

    @Value("${jms.brokerUrl}")
    private String brokerUrl;

    private final JmsErrorHandler jmsErrorHandler;

    @Autowired
    public JMSConfiguration(JmsErrorHandler jmsErrorHandler) {
        this.jmsErrorHandler = jmsErrorHandler;
    }


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

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory
                = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setErrorHandler(this.jmsErrorHandler);
        return factory;
    }

}
