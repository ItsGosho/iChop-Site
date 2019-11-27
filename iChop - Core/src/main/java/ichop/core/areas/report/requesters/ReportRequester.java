package ichop.core.areas.report.requesters;

import ichop.core.areas.report.models.jms.IsUserReportedRequest;
import ichop.core.areas.report.models.jms.ReportCreateRequest;
import ichop.core.areas.report.models.jms.ReportDeleteByIdRequest;
import org.ichop.commons.domain.JmsReplyModel;

public interface ReportRequester {

    JmsReplyModel create(ReportCreateRequest request);
    JmsReplyModel deleteById(ReportDeleteByIdRequest request);
    JmsReplyModel isUserReported(IsUserReportedRequest request);

}
