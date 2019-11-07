package ichop.core.areas.report.models.jms.create;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public abstract class ReportCreateRequest extends RequestCandidate {

    private String userId;
    private String reason;


    /*TODO: user has not created any reports for a provided thread or for comment with type*/

}
