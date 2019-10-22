package ichop.threads.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.threads.common.aop.JmsAfterReturn;
import ichop.threads.common.aop.JmsValidate;
import ichop.threads.domain.models.jms.create.ThreadCreateRequest;
import ichop.threads.domain.models.jms.create.ThreadCreateReply;
import ichop.threads.domain.models.jms.delete.ThreadDeleteByIdReply;
import ichop.threads.domain.models.jms.delete.ThreadDeleteByIdRequest;
import ichop.threads.domain.models.jms.increase.ThreadIncreaseViewsReply;
import ichop.threads.domain.models.jms.increase.ThreadIncreaseViewsRequest;
import ichop.threads.domain.models.jms.retrieve.ThreadGetByIdReply;
import ichop.threads.domain.models.jms.retrieve.ThreadGetByIdRequest;
import ichop.threads.domain.models.service.ThreadServiceModel;
import ichop.threads.common.helpers.JmsHelper;
import ichop.threads.services.ThreadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.threads.common.configurations.JmsFactories.QUEUE;
import static ichop.threads.constants.ThreadReplyConstants.*;

@Component
public class ThreadJmsListener {

    private final JmsHelper jmsHelper;
    private final ObjectMapper objectMapper;
    private final ThreadServices threadServices;

    @Autowired
    public ThreadJmsListener(JmsHelper jmsHelper, ObjectMapper objectMapper, ThreadServices threadServices) {
        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;
        this.threadServices = threadServices;
    }

    @JmsValidate(model = ThreadCreateRequest.class)
    @JmsAfterReturn
    @JmsListener(destination = "${artemis.queue.thread.create}", containerFactory = QUEUE)
    public ThreadCreateReply createThread(Message message) {
        ThreadCreateRequest requestModel = this.jmsHelper.getResultModel(message, ThreadCreateRequest.class);

        ThreadServiceModel thread = this.objectMapper.convertValue(requestModel, ThreadServiceModel.class);

        ThreadCreateReply replyModel = this.threadServices.save(thread, ThreadCreateReply.class);
        replyModel.setMessage(THREAD_CREATED_SUCCESSFUL);

        return replyModel;
    }

    @JmsValidate(model = ThreadGetByIdRequest.class)
    @JmsListener(destination = "${artemis.queue.thread.get_by_id}", containerFactory = QUEUE)
    public ThreadGetByIdReply getById(Message message) {
        ThreadGetByIdRequest requestModel = this.jmsHelper.getResultModel(message, ThreadGetByIdRequest.class);

        ThreadGetByIdReply replyModel = this.threadServices.findById(requestModel.getId(), ThreadGetByIdReply.class);
        replyModel.setMessage(THREAD_RETRIEVED_SUCCESSFUL);

        return replyModel;
    }

    @JmsValidate(model = ThreadIncreaseViewsRequest.class)
    @JmsListener(destination = "${artemis.queue.thread.increase_views}", containerFactory = "queueFactory")
    public ThreadIncreaseViewsReply increaseViews(Message message) {
        ThreadIncreaseViewsRequest requestModel = this.jmsHelper.getResultModel(message, ThreadIncreaseViewsRequest.class);

        this.threadServices.increaseViews(requestModel.getId());

        ThreadIncreaseViewsReply replyModel = new ThreadIncreaseViewsReply();
        replyModel.setMessage(THREAD_VIEW_INCREASED_SUCCESSFUL);

        return replyModel;
    }

    @JmsValidate(model = ThreadDeleteByIdRequest.class)
    @JmsListener(destination = "${artemis.queue.thread.delete_by_id}", containerFactory = "queueFactory")
    public ThreadDeleteByIdReply deleteById(Message message) {
        ThreadDeleteByIdRequest requestModel = this.jmsHelper.getResultModel(message, ThreadDeleteByIdRequest.class);

        this.threadServices.deleteById(requestModel.getId());

        ThreadDeleteByIdReply replyModel = new ThreadDeleteByIdReply();
        replyModel.setMessage(THREAD_DELETED_SUCCESSFUL);

        return replyModel;
    }

}
