package ichop.threads.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.common.jms.aop.JmsAfterReturn;
import ichop.common.jms.aop.JmsValidate;
import ichop.threads.domain.models.jms.create.ThreadCreateRequestModel;
import ichop.threads.domain.models.jms.create.ThreadCreateReplyModel;
import ichop.threads.domain.models.jms.delete.ThreadDeleteByIdReplyModel;
import ichop.threads.domain.models.jms.delete.ThreadDeleteByIdRequestModel;
import ichop.threads.domain.models.jms.increase.ThreadIncreaseViewsReplyModel;
import ichop.threads.domain.models.jms.increase.ThreadIncreaseViewsRequestModel;
import ichop.threads.domain.models.jms.retrieve.ThreadGetByIdReplyModel;
import ichop.threads.domain.models.jms.retrieve.ThreadGetByIdRequestModel;
import ichop.threads.domain.models.service.ThreadServiceModel;
import ichop.common.jms.helpers.JmsHelper;
import ichop.threads.services.ThreadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class ThreadJmsListenerImp implements ThreadJmsListener {

    private static final String THREAD_CREATED_SUCCESSFUL = "Thread created successful!";
    private static final String THREAD_RETRIEVED_SUCCESSFUL = "Thread retrieved successful!";
    private static final String THREAD_VIEW_INCREASED_SUCCESSFUL = "Thread views increased successful!";
    private static final String THREAD_DELETED_SUCCESSFUL = "Thread deleted successful!";

    private final JmsHelper jmsHelper;
    private final ObjectMapper objectMapper;
    private final ThreadServices threadServices;

    @Autowired
    public ThreadJmsListenerImp(JmsHelper jmsHelper, ObjectMapper objectMapper, ThreadServices threadServices) {
        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;
        this.threadServices = threadServices;
    }

    @Override
    @JmsValidate(model = ThreadCreateRequestModel.class)
    @JmsAfterReturn
    @JmsListener(destination = "${artemis.queue.thread.create}", containerFactory = "queueFactory")
    public ThreadCreateReplyModel createThread(Message message) {
        ThreadCreateRequestModel requestModel = this.jmsHelper.getResultModel(message, ThreadCreateRequestModel.class);

        ThreadServiceModel thread = this.objectMapper.convertValue(requestModel, ThreadServiceModel.class);
        thread = this.threadServices.save(thread);

        ThreadCreateReplyModel replyModel = this.objectMapper.convertValue(thread, ThreadCreateReplyModel.class);
        replyModel.setMessage(THREAD_CREATED_SUCCESSFUL);

        return replyModel;
    }

    @Override
    @JmsValidate(model = ThreadGetByIdRequestModel.class)
    @JmsListener(destination = "${artemis.queue.thread.get_by_id}", containerFactory = "queueFactory")
    public ThreadGetByIdReplyModel getById(Message message) {
        ThreadGetByIdRequestModel requestModel = this.jmsHelper.getResultModel(message, ThreadGetByIdRequestModel.class);

        ThreadServiceModel thread = this.threadServices.findById(requestModel.getId());

        ThreadGetByIdReplyModel replyModel = this.objectMapper.convertValue(thread, ThreadGetByIdReplyModel.class);
        replyModel.setMessage(THREAD_RETRIEVED_SUCCESSFUL);

        return replyModel;
    }

    @Override
    @JmsValidate(model = ThreadIncreaseViewsRequestModel.class)
    @JmsListener(destination = "${artemis.queue.thread.increase_views}", containerFactory = "queueFactory")
    public ThreadIncreaseViewsReplyModel increaseViews(Message message) {
        ThreadIncreaseViewsRequestModel requestModel = this.jmsHelper.getResultModel(message, ThreadIncreaseViewsRequestModel.class);

        this.threadServices.increaseViews(requestModel.getId());

        ThreadIncreaseViewsReplyModel replyModel = new ThreadIncreaseViewsReplyModel();
        replyModel.setMessage(THREAD_VIEW_INCREASED_SUCCESSFUL);

        return replyModel;
    }

    @Override
    @JmsValidate(model = ThreadDeleteByIdRequestModel.class)
    @JmsListener(destination = "${artemis.queue.thread.delete_by_id}", containerFactory = "queueFactory")
    public ThreadDeleteByIdReplyModel deleteById(Message message) {
        ThreadDeleteByIdRequestModel requestModel = this.jmsHelper.getResultModel(message, ThreadDeleteByIdRequestModel.class);

        this.threadServices.deleteById(requestModel.getId());

        ThreadDeleteByIdReplyModel replyModel = new ThreadDeleteByIdReplyModel();
        replyModel.setMessage(THREAD_DELETED_SUCCESSFUL);

        return replyModel;
    }

}
