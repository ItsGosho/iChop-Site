package ichop.core.areas.report.models.jms;

import ichop.core.areas.report.models.ReportOn;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class ReportDeleteByIdRequest extends RequestCandidate {

    private String id;
    private ReportOn type;

}
