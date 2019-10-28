package ichop.storage.common.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
@Profile("!test")
public class ExecutorsConfiguration {

    @Bean(name = "jmsQueueExecutor")
    public ExecutorService getThreadPoolTaskExecutor() {
        return Executors.newCachedThreadPool();
    }

}
