package ichop.reports.domain.models.jms.all.pageable.reply;

import ichop.reports.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class ReportsAllPageableReply extends BaseReplyModel {

    private String id;
    private String userId;
    private String reason;
    private LocalDateTime reportedOn;

}
