package ichop.service.threads.report.crud;

import ichop.domain.models.service.threads.report.CommentReportServiceModel;
import ichop.domain.models.service.threads.report.ThreadReportServiceModel;

public interface ReportCrudServices {

    ThreadReportServiceModel save(ThreadReportServiceModel threadReportServiceModel);
    CommentReportServiceModel save(CommentReportServiceModel commentReportServiceModel);

}
