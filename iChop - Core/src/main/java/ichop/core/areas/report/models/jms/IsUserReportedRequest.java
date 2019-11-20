package ichop.core.areas.report.models.jms;

import ichop.core.areas.report.models.ReportOn;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class IsUserReportedRequest extends RequestCandidate {

    private ReportOn type;
    private String entityId;
    private String userId;
}
