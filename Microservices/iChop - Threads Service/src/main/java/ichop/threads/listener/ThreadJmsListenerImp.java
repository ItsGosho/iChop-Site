package ichop.threads.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.threads.domain.models.jms.ThreadCreateRequestModel;
import ichop.threads.domain.models.jms.ThreadCreateReplyModel;
import ichop.threads.domain.models.jms.ThreadGetByIdReplyModel;
import ichop.threads.domain.models.jms.ThreadGetByIdRequestModel;
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
    private static final String THREAD_RETRIEVED_SUCCESSFUL = "Thread retrieved successful!";

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

        ThreadCreateRequestModel requestModel = this.jmsHelper.getResultModel(message, ThreadCreateRequestModel.class);

        if (!this.validationHelper.isValid(requestModel)) {
            this.jmsHelper.replyValidationError(message, requestModel);
            return;
        }

        ThreadServiceModel thread = this.objectMapper.convertValue(requestModel, ThreadServiceModel.class);
        this.threadServices.save(thread);

        ThreadCreateReplyModel replyModel = this.objectMapper.convertValue(thread, ThreadCreateReplyModel.class);
        replyModel.setMessage(THREAD_CREATED_SUCCESSFUL);

        this.jmsHelper.replySuccessful(message, replyModel);
    }

    @JmsListener(destination = "${artemis.queue.thread.get_by_id}", containerFactory = "queueFactory")
    private void getById(Message message) {

        ThreadGetByIdRequestModel requestModel = this.jmsHelper.getResultModel(message, ThreadGetByIdRequestModel.class);

        if (!this.validationHelper.isValid(requestModel)) {
            this.jmsHelper.replyValidationError(message, requestModel);
            return;
        }

        ThreadServiceModel thread = this.threadServices.findById(requestModel.getId());

        ThreadGetByIdReplyModel replyModel = this.objectMapper.convertValue(thread, ThreadGetByIdReplyModel.class);
        replyModel.setMessage(THREAD_RETRIEVED_SUCCESSFUL);

        this.jmsHelper.replySuccessful(message, replyModel);

    }

}
