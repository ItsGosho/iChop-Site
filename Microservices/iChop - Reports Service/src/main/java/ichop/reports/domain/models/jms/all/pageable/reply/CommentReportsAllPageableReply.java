package ichop.reports.domain.models.jms.all.pageable.reply;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReportsAllPageableReply extends ReportsAllPageableReply {

    private String commentId;
    private Type type;

}
