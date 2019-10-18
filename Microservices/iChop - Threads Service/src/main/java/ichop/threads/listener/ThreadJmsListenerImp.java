package ichop.threads.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.threads.domain.models.jms.ThreadCreateReceiveModel;
import ichop.threads.domain.models.jms.ThreadCreateReplyModel;
import ichop.threads.domain.models.service.ThreadServiceModel;
import ichop.threads.helpers.JmsHelper;
import ichop.threads.helpers.ValidationHelper;
import ichop.threads.services.ThreadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class ThreadJmsListenerImp implements ThreadJmsListener {

    private static final String THREAD_CREATED_SUCCESSFUL = "Thread created successful!";

    private final JmsHelper jmsHelper;
    private final ValidationHelper validationHelper;
    private final ObjectMapper objectMapper;
    private final ThreadServices threadServices;

    @Autowired
    public ThreadJmsListenerImp(JmsHelper jmsHelper, ValidationHelper validationHelper, ObjectMapper objectMapper, ThreadServices threadServices) {
        this.jmsHelper = jmsHelper;
        this.validationHelper = validationHelper;
        this.objectMapper = objectMapper;
        this.threadServices = threadServices;
    }


    @JmsListener(destination = "${artemis.queue.thread.create}", containerFactory = "queueFactory")
    private void createThread(Message message) {

        ThreadCreateReceiveModel receiveModel = this.jmsHelper.getResultModel(message, ThreadCreateReceiveModel.class);

        if (!this.validationHelper.isValid(receiveModel)) {
            this.jmsHelper.replyValidationError(message, receiveModel);
            return;
        }

        ThreadServiceModel thread = this.objectMapper.convertValue(receiveModel, ThreadServiceModel.class);
        this.threadServices.save(thread);

        ThreadCreateReplyModel replyModel = this.objectMapper.convertValue(thread, ThreadCreateReplyModel.class);
        replyModel.setMessage(THREAD_CREATED_SUCCESSFUL);

        this.jmsHelper.replySuccessful(message, replyModel);
    }

}
