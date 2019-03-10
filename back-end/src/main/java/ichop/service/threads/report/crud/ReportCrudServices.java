package ichop.service.threads.report.crud;

import ichop.domain.models.service.threads.report.CommentReportServiceModel;
import ichop.domain.models.service.threads.report.ThreadReportServiceModel;

import java.util.List;

public interface ReportCrudServices {

    ThreadReportServiceModel save(ThreadReportServiceModel threadReport);
    CommentReportServiceModel save(CommentReportServiceModel commentReport);

}
