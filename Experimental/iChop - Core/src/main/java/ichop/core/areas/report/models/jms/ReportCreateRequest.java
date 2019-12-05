package ichop.core.areas.report.models.jms;

import ichop.core.areas.report.models.ReportOn;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class ReportCreateRequest extends RequestCandidate {

    private String creatorUsername;
    private String reason;
    private ReportOn type;
    private String entityId;

}
