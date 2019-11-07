package ichop.core.areas.report.models.jms.create;

import ichop.core.areas.report.models.ReportOn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReportCreateRequest extends ReportCreateRequest {

    private String commentId;
    private ReportOn type;

}
