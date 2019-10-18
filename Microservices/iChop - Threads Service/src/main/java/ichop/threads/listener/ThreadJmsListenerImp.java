package ichop.threads.listener;

import ichop.threads.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class ThreadJmsListenerImp implements ThreadJmsListener {

    private final JmsHelper jmsHelper;

    @Autowired
    public ThreadJmsListenerImp(JmsHelper jmsHelper) {
        this.jmsHelper = jmsHelper;
    }


    @JmsListener(destination = "${TODO}", containerFactory = "taskExecutorCustom")
    private void TODO(Message message) {

    }

}
