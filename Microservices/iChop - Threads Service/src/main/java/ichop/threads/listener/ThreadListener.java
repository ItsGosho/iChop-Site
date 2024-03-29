package ichop.threads.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.threads.domain.models.jms.ThreadReply;
import ichop.threads.domain.models.jms.create.ThreadCreateRequest;
import ichop.threads.domain.models.jms.delete.ThreadDeleteByIdRequest;
import ichop.threads.domain.models.jms.increase.ThreadIncreaseViewsRequest;
import ichop.threads.domain.models.jms.retrieve.ThreadFindByIdRequest;
import ichop.threads.domain.models.jms.retrieve.ThreadsFindAllRequest;
import ichop.threads.domain.models.service.ThreadServiceModel;
import ichop.threads.services.ThreadServices;
import org.ichop.commons.aop.JmsAfterReturn;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.domain.EmptyReply;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static ichop.threads.constants.ThreadReplyConstants.*;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

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
    public ThreadReply createThread(Message message) {
        ThreadCreateRequest requestModel = this.jmsHelper.toModel(message, ThreadCreateRequest.class);

        ThreadServiceModel thread = this.objectMapper.convertValue(requestModel, ThreadServiceModel.class);

        return this.threadServices.save(thread, ThreadReply.class);
    }

    @JmsValidate(model = ThreadFindByIdRequest.class)
    @JmsAfterReturn(message = FETCH_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.threads.find.by.id}", containerFactory = QUEUE)
    public ThreadReply getById(Message message) {
        ThreadFindByIdRequest requestModel = this.jmsHelper.toModel(message, ThreadFindByIdRequest.class);

        return this.threadServices.findById(requestModel.getId(), ThreadReply.class);
    }

    /*TODO: refactor the way of proceeding a pageable*/
    @JmsValidate(model = ThreadsFindAllRequest.class)
    @JmsAfterReturn(message = FETCH_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.threads.find.all}", containerFactory = QUEUE)
    public List<ThreadReply> all(Message message) throws IOException {
        ThreadsFindAllRequest requestModel = this.jmsHelper.toModel(message, ThreadsFindAllRequest.class);
        Map<String,Object> pageableData = requestModel.getPageable();

        int page = Integer.parseInt(pageableData.get("pageNumber").toString());
        int size = Integer.parseInt(pageableData.get("pageSize").toString());
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Order.desc("createdOn")));

        return this.threadServices.findAll(pageable,ThreadReply.class);
    }



    @JmsAfterReturn(message = FETCH_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.threads.find.total}", containerFactory = QUEUE)
    public Long total(Message message) {
        return this.threadServices.findTotal();
    }

    @JmsValidate(model = ThreadIncreaseViewsRequest.class)
    @JmsAfterReturn(message = THREAD_VIEW_INCREASED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.threads.increase_views}", containerFactory = QUEUE)
    public ThreadReply increaseViews(Message message) {
        ThreadIncreaseViewsRequest requestModel = this.jmsHelper.toModel(message, ThreadIncreaseViewsRequest.class);

        return this.threadServices.increaseViews(requestModel.getId(),ThreadReply.class);
    }

    @JmsValidate(model = ThreadDeleteByIdRequest.class)
    @JmsAfterReturn(message = THREAD_DELETED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.threads.delete.by.id}", containerFactory = QUEUE)
    public EmptyReply deleteById(Message message) {
        ThreadDeleteByIdRequest requestModel = this.jmsHelper.toModel(message, ThreadDeleteByIdRequest.class);

        this.threadServices.deleteById(requestModel.getId());

        return new EmptyReply();
    }

}
