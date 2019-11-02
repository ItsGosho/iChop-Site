package ichop.threads.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.threads.common.aop.JmsAfterReturn;
import ichop.threads.common.aop.JmsValidate;
import ichop.threads.common.helpers.BaseListener;
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

import static ichop.threads.common.constants.JmsFactories.QUEUE;
import static ichop.threads.constants.ThreadReplyConstants.*;

@Component
public class ThreadListener extends BaseListener {

    private final ThreadServices threadServices;

    @Autowired
    protected ThreadListener(JmsHelper jmsHelper, ObjectMapper objectMapper, ThreadServices threadServices) {
        super(jmsHelper, objectMapper);
        this.threadServices = threadServices;
    }


    @JmsValidate(model = ThreadCreateRequest.class)
    @JmsAfterReturn(message = THREAD_CREATED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.threads.create}", containerFactory = QUEUE)
    public ThreadCreateReply createThread(Message message) {
        ThreadCreateRequest requestModel = this.jmsHelper.getResultModel(message, ThreadCreateRequest.class);

        ThreadServiceModel thread = this.objectMapper.convertValue(requestModel, ThreadServiceModel.class);

        return this.threadServices.save(thread, ThreadCreateReply.class);
    }

    @JmsValidate(model = ThreadGetByIdRequest.class)
    @JmsAfterReturn(message = THREAD_RETRIEVED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.threads.find.by.id}", containerFactory = QUEUE)
    public ThreadGetByIdReply getById(Message message) {
        ThreadGetByIdRequest requestModel = this.jmsHelper.getResultModel(message, ThreadGetByIdRequest.class);

        return this.threadServices.findById(requestModel.getId(), ThreadGetByIdReply.class);
    }

    @JmsValidate(model = ThreadIncreaseViewsRequest.class)
    @JmsAfterReturn(message = THREAD_VIEW_INCREASED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.threads.increase_views}", containerFactory = QUEUE)
    public ThreadIncreaseViewsReply increaseViews(Message message) {
        ThreadIncreaseViewsRequest requestModel = this.jmsHelper.getResultModel(message, ThreadIncreaseViewsRequest.class);

        this.threadServices.increaseViews(requestModel.getId());

        return new ThreadIncreaseViewsReply();
    }

    @JmsValidate(model = ThreadDeleteByIdRequest.class)
    @JmsAfterReturn(message = THREAD_DELETED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.threads.delete.by.id}", containerFactory = QUEUE)
    public ThreadDeleteByIdReply deleteById(Message message) {
        ThreadDeleteByIdRequest requestModel = this.jmsHelper.getResultModel(message, ThreadDeleteByIdRequest.class);

        this.threadServices.deleteById(requestModel.getId());

        return new ThreadDeleteByIdReply();
    }

}
