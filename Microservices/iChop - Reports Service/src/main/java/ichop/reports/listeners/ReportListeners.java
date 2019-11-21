package ichop.reports.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reports.domain.models.jms.ReportReply;
import ichop.reports.domain.models.jms.ReportCreateRequest;
import ichop.reports.domain.models.jms.ReportDeleteByIdRequest;
import ichop.reports.domain.models.jms.IsUserReportedRequest;
import ichop.reports.domain.models.service.ReportServiceModel;
import ichop.reports.services.ReportServices;
import org.ichop.commons.aop.JmsAfterReturn;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.domain.EmptyReply;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.reports.constants.ReportReplyConstants.*;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

@Component
public class ReportListeners extends BaseListener {


    private final ReportServices reportServices;

    @Autowired
    protected ReportListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, ReportServices reportServices) {
        super(jmsHelper, objectMapper);
        this.reportServices = reportServices;
    }


    @JmsValidate(model = ReportCreateRequest.class)
    @JmsAfterReturn(message = REPORT_CREATED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.create}", containerFactory = QUEUE)
    public ReportReply create(Message message) {
        ReportCreateRequest requestModel = this.jmsHelper.toModel(message, ReportCreateRequest.class);

        ReportServiceModel report = this.objectMapper.convertValue(requestModel, ReportServiceModel.class);

        return this.reportServices.save(report, ReportReply.class);
    }

    @JmsValidate(model = ReportDeleteByIdRequest.class)
    @JmsAfterReturn(message = REPORT_DELETED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.delete.by.id}", containerFactory = QUEUE)
    public EmptyReply deleteById(Message message) {
        ReportDeleteByIdRequest requestModel = this.jmsHelper.toModel(message, ReportDeleteByIdRequest.class);

        this.reportServices.deleteById(requestModel.getType(), requestModel.getId());

        return new EmptyReply();
    }

    @JmsValidate(model = IsUserReportedRequest.class)
    @JmsAfterReturn(message = FETCH_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reports.is.user.reported}", containerFactory = QUEUE)
    public BoolReply isUserReported(Message message) {
        IsUserReportedRequest requestModel = this.jmsHelper.toModel(message, IsUserReportedRequest.class);

        boolean hasReported = this.reportServices.hasReported(requestModel.getType(), requestModel.getCreatorUsername(), requestModel.getCreatorUsername());

        return new BoolReply(hasReported);
    }
}
