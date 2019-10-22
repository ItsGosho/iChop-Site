package ichop.reports.services;

import ichop.reports.domain.enums.Type;
import ichop.reports.domain.models.service.CommentReportServiceModel;

public interface CommentReportServices extends ReportServices<CommentReportServiceModel> {

    boolean hasReported(String userId, String commentId, Type type);

}
