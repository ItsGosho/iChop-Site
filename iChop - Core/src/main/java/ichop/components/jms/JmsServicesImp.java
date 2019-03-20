package ichop.components.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsServicesImp implements JmsServices {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsServicesImp(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }



}
