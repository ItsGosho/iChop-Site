package ichop.service.report;

import ichop.domain.models.service.report.CommentReportServiceModel;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.report.ThreadReportServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.report.PostReportServiceModel;
import ichop.domain.models.service.post.PostServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface ReportServices {


    ThreadReportServiceModel addReport(ThreadServiceModel thread, UserServiceModel user,String reason);
    CommentReportServiceModel addReport(CommentServiceModel comment, UserServiceModel user, String reason);
    PostReportServiceModel addReport(PostServiceModel post, UserServiceModel user, String reason);

}
