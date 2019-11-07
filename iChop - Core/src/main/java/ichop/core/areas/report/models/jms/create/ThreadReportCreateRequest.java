package ichop.core.areas.report.models.jms.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadReportCreateRequest extends ReportCreateRequest {

    private String threadId;

}
