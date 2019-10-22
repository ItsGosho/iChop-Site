package ichop.reports.domain.models.jms.create.reply;

import ichop.reports.common.domain.BaseReplyModel;
import ichop.reports.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class ReportCreateReply extends BaseReplyModel {


    private String id;
    private String userId;
    private String reason;
    private String reportedOn;

}
