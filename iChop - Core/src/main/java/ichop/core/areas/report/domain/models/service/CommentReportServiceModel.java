package ichop.core.areas.report.domain.models.service;

import ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReportServiceModel extends BaseReportServiceModel {

    private CommentServiceModel comment;

}
