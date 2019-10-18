package ichop.threads.listener;

import ichop.threads.domain.models.jms.ThreadCreateReceiveModel;
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


    @JmsListener(destination = "${artemis.queue.thread.create}", containerFactory = "queueFactory")
    private void TODO(Message message) {
        ThreadCreateReceiveModel receiveModel = this.jmsHelper.getResultModel(message, ThreadCreateReceiveModel.class);


    }

}
