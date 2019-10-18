package ichop.threads.listener;

import ichop.threads.domain.models.jms.ErrorSendModel;
import ichop.threads.domain.models.jms.ThreadCreateSendModel;
import ichop.threads.helpers.JmsHelper;
import ichop.threads.helpers.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class ThreadJmsListenerImp implements ThreadJmsListener {

    private final JmsHelper jmsHelper;
    private final ValidationHelper validationHelper;

    @Autowired
    public ThreadJmsListenerImp(JmsHelper jmsHelper, ValidationHelper validationHelper) {
        this.jmsHelper = jmsHelper;
        this.validationHelper = validationHelper;
    }


    @JmsListener(destination = "${artemis.queue.thread.create}", containerFactory = "queueFactory")
    private void TODO(Message message) throws JMSException {

        ThreadCreateSendModel receiveModel = this.jmsHelper.getResultModel(message, ThreadCreateSendModel.class);

        if (!this.validationHelper.isValid(receiveModel)) {
            this.jmsHelper.replyValidationError(message,receiveModel);

            return;
        }

        /*TODO:
         *  1.Validate the model via the validation helper
         *  2.if error to think of better return model
         *  3.if not to create it
         *  4.then to return the created thread and that is successful
         *
         * */
    }

}
