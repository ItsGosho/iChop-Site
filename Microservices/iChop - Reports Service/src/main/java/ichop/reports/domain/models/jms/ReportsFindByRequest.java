package ichop.reports.domain.models.jms;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class ReportsFindByRequest extends RequestCandidate {

    private String type;

}
