package ichop.reports.services;

import ichop.reports.domain.models.service.ThreadReportServiceModel;

public interface ThreadReportServices extends ReportServices<ThreadReportServiceModel> {

    boolean existsByThreadId(String threadId);
    boolean hasReported(String userId, String threadId);
    boolean deleteByThreadId(String threadId);

}
