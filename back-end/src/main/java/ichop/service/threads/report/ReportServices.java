package ichop.service.threads.report;

import ichop.domain.models.service.threads.report.CommentReportServiceModel;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.report.ThreadReportServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface ReportServices {


    ThreadReportServiceModel addReport(ThreadServiceModel thread, UserServiceModel user,String reason);
    CommentReportServiceModel addReport(CommentServiceModel comment, UserServiceModel user, String reason);

}
