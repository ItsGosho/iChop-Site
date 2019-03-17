package ichop.service.report;

import ichop.domain.models.service.report.ThreadReportServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface ThreadReportServices {

    ThreadReportServiceModel createReport(ThreadServiceModel thread, UserServiceModel user, String reason);

}
