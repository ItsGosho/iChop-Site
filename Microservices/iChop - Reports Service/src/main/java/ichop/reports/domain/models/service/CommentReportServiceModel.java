package ichop.reports.domain.models.service;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReportServiceModel extends ReportServiceModel {

    private String commentId;
    private Type type;

}
