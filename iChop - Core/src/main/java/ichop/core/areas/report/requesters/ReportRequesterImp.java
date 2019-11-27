package ichop.core.areas.report.requesters;

import ichop.core.areas.report.models.jms.IsUserReportedRequest;
import ichop.core.areas.report.models.jms.ReportCreateRequest;
import ichop.core.areas.report.models.jms.ReportDeleteByIdRequest;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReportRequesterImp implements ReportRequester {

    private final JmsHelper jmsHelper;

    private final String createDestination;
    private final String deleteByIdDestination;
    private final String isUserReportedDestination;

    @Autowired
    public ReportRequesterImp(JmsHelper jmsHelper,
                              @Value("${artemis.queue.reports.create}") String createDestination,
                              @Value("${artemis.queue.reports.delete.by.id}") String deleteByIdDestination,
                              @Value("${artemis.queue.reports.is.user.reported}") String isUserReportedDestination) {

        this.jmsHelper = jmsHelper;

        this.createDestination = createDestination;
        this.deleteByIdDestination = deleteByIdDestination;
        this.isUserReportedDestination = isUserReportedDestination;
    }

    @Override
    public JmsReplyModel create(ReportCreateRequest request) {
        return this.jmsHelper.sendAndReceive(this.createDestination, request);
    }

    @Override
    public JmsReplyModel deleteById(ReportDeleteByIdRequest request) {
        return this.jmsHelper.sendAndReceive(this.deleteByIdDestination, request);
    }

    @Override
    public JmsReplyModel isUserReported(IsUserReportedRequest request) {
        return this.jmsHelper.sendAndReceive(this.isUserReportedDestination, request);
    }
}
