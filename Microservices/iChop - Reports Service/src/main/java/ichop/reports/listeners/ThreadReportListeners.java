package ichop.reports.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reports.common.aop.JmsAfterReturn;
import ichop.reports.common.aop.JmsValidate;
import ichop.reports.common.helpers.JmsHelper;
import ichop.reports.domain.models.jms.create.reply.ThreadReportCreateReply;
import ichop.reports.domain.models.jms.create.request.ThreadReportCreateRequest;
import ichop.reports.domain.models.service.ThreadReportServiceModel;
import ichop.reports.services.ThreadReportServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.reports.common.constants.JmsFactories.QUEUE;
import static ichop.reports.constants.ReportReplyConstants.REPORT_CREATED_SUCCESSFUL;

@Component
public class ThreadReportListeners {

    private final JmsHelper jmsHelper;
    private final ObjectMapper objectMapper;
    private final ThreadReportServices threadReportServices;

    @Autowired
    public ThreadReportListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, ThreadReportServices threadReportServices) {
        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;
        this.threadReportServices = threadReportServices;
    }


    @JmsValidate(model = ThreadReportCreateRequest.class)
    @JmsAfterReturn
    @JmsListener(destination = "${artemis.queue.reports.thread.create}", containerFactory = QUEUE)
    public ThreadReportCreateReply create(Message message) {
        ThreadReportCreateRequest requestModel = this.jmsHelper.getResultModel(message, ThreadReportCreateRequest.class);

        ThreadReportServiceModel threadReport = this.objectMapper.convertValue(requestModel, ThreadReportServiceModel.class);

        ThreadReportCreateReply replyModel = this.threadReportServices.save(threadReport, ThreadReportCreateReply.class);
        replyModel.setMessage(REPORT_CREATED_SUCCESSFUL);

        return replyModel;
    }

}
