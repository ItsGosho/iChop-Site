package ichop.reports.domain.models.jms.delete.reply;

import ichop.reports.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentReportDeleteByIdReply extends BaseReplyModel {

    public CommentReportDeleteByIdReply(String message) {
        super(message);
    }
}
