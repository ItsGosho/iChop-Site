package ichop.reports.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reports.domain.models.jms.ThreadReportReply;
import ichop.reports.domain.models.jms.all.pageable.ThreadReportsAllPageableRequest;
import ichop.reports.domain.models.jms.create.ThreadReportCreateRequest;
import ichop.reports.domain.models.jms.delete.ThreadReportDeleteByIdRequest;
import ichop.reports.domain.models.service.ThreadReportServiceModel;
import ichop.reports.services.ThreadReportServices;
import org.ichop.commons.aop.JmsAfterReturn;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.domain.EmptyReply;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.util.List;

import static ichop.reports.constants.ReportReplyConstants.*;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

@Component
public class ThreadReportListeners extends BaseListener {


    private final ThreadReportServices threadReportServices;

    @Autowired
    protected ThreadReportListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, ThreadReportServices threadReportServices) {
        super(jmsHelper, objectMapper);
        this.threadReportServices = threadReportServices;
    }


    @JmsValidate(model = ThreadReportCreateRequest.class)
    @JmsAfterReturn(message = REPORT_CREATED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.thread.create}", containerFactory = QUEUE)
    public ThreadReportReply create(Message message) {
        ThreadReportCreateRequest requestModel = this.jmsHelper.toModel(message, ThreadReportCreateRequest.class);

        ThreadReportServiceModel threadReport = this.objectMapper.convertValue(requestModel, ThreadReportServiceModel.class);

        return this.threadReportServices.save(threadReport, ThreadReportReply.class);
    }

    @JmsValidate(model = ThreadReportDeleteByIdRequest.class)
    @JmsAfterReturn(message = REPORT_DELETED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.thread.delete.by.id}", containerFactory = QUEUE)
    public EmptyReply deleteByThreadId(Message message) {
        ThreadReportDeleteByIdRequest requestModel = this.jmsHelper.toModel(message, ThreadReportDeleteByIdRequest.class);

        this.threadReportServices.deleteByThreadId(requestModel.getThreadId());

        return new EmptyReply();
    }

    @JmsValidate(model = ThreadReportsAllPageableRequest.class)
    @JmsAfterReturn(message = REPORTS_FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.thread.find.pageable}", containerFactory = QUEUE)
    public List<ThreadReportReply> allPageable(Message message) {
        ThreadReportsAllPageableRequest requestModel = this.jmsHelper.toModel(message, ThreadReportsAllPageableRequest.class);

        return this.threadReportServices.findAll(requestModel.getPageable(), ThreadReportReply.class);
    }

}
