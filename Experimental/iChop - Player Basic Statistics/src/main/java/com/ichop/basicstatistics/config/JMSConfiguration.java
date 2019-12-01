package com.ichop.basicstatistics.config;

import com.ichop.basicstatistics.constants.PlayerStatisticsJMSConstants;
import com.ichop.basicstatistics.handlers.JmsErrorHandler;
import com.ichop.basicstatistics.listeners.PlayerStatisticsJMSListener;
import com.ichop.basicstatistics.listeners.annotations.GetPlayerDataByUUIDListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Map;

import static com.ichop.basicstatistics.constants.JmsConstants.RECEIVE_TIMEOUT;

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
    public JmsTemplate jmsTemplate() {
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
