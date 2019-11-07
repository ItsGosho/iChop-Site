package ichop.CHANGE.common.configurations;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import java.util.concurrent.ExecutorService;

import static ichop.CHANGE.constants.ConditionalConstants.ARTEMIS_CONFIGURATION;

@Configuration
@ConditionalOnProperty(
        name = ARTEMIS_CONFIGURATION,
        havingValue = "true",
        matchIfMissing = true
)
public class ArtemisConfiguration {

    @Bean
    @Primary
    public ActiveMQConnectionFactory connectionFactory(@Value("${artemis.broker-url}") String brokerUrl,
                                                       @Value("${artemis.username}") String username,
                                                       @Value("${artemis.password}") String password
    ) {
        return new ActiveMQConnectionFactory(brokerUrl, username, password);
    }

    @Bean
    public JmsListenerContainerFactory queueFactory(@Qualifier("jmsQueueExecutor") ExecutorService executorService, ActiveMQConnectionFactory connectionFactory) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setCacheLevel(DefaultMessageListenerContainer.CACHE_NONE);
        factory.setConnectionFactory(connectionFactory);
        factory.setTaskExecutor(executorService);
        factory.setConcurrency("1-50");
        factory.setSessionTransacted(false);

        return factory;
    }
}
