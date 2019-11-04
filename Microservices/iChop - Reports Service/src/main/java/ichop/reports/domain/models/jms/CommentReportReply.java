package ichop.reports.domain.models.jms;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReportReply extends ReportReply {

    private String commentId;
    private Type type;
    
}
