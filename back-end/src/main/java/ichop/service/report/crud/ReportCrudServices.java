package ichop.service.report.crud;

import ichop.domain.models.service.report.CommentReportServiceModel;
import ichop.domain.models.service.report.ThreadReportServiceModel;
import ichop.domain.models.service.report.PostReportServiceModel;

public interface ReportCrudServices {

    ThreadReportServiceModel save(ThreadReportServiceModel threadReport);
    CommentReportServiceModel save(CommentReportServiceModel commentReport);
    PostReportServiceModel save(PostReportServiceModel postReport);

}
