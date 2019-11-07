package ichop.core.areas.report.models.jms;

import ichop.core.areas.report.models.ReportOn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReportReply extends ReportReply {

    private String commentId;
    private ReportOn type;
    
}
