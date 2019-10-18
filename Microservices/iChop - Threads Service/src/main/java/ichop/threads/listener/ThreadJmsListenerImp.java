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

        /*TODO:
        *  1.Validate the model via the validation helper
        *  2.if error to think of better return model
        *  3.if not to create it
        *  4.then to return the created thread and that is successful
        *
        * */
    }

}
