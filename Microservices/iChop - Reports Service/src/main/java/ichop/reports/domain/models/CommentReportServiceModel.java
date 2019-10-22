package ichop.reports.domain.models;

import ichop.reports.domain.entities.Report;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CommentReportServiceModel extends ReportServiceModel {

    private String commentId;

}
