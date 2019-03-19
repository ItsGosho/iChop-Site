package ichop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class CLR implements CommandLineRunner {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public CLR(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
         this.jmsTemplate.convertAndSend("test-123",7777);
    }
}
