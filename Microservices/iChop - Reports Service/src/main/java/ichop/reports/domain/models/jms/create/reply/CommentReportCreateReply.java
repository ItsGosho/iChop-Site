package ichop.reports.domain.models.jms.create.reply;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReportCreateReply extends ReportCreateReply {

    private String commentId;
    private Type type;

}
