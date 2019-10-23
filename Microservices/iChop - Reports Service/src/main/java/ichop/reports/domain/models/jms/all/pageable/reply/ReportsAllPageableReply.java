package ichop.reports.domain.models.jms.all.pageable.reply;

import ichop.reports.common.domain.BaseReplyModel;
import ichop.reports.domain.models.service.ReportServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportsAllPageableReply<R extends ReportServiceModel> extends BaseReplyModel {

    private List<R> reports;

}
