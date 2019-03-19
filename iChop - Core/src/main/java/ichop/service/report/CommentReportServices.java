package ichop.service.report;

import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.report.CommentReportServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface CommentReportServices {

    CommentReportServiceModel createReport(CommentServiceModel comment, UserServiceModel user, String reason);

}
