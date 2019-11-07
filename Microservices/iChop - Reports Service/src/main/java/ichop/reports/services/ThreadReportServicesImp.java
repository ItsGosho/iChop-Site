package ichop.reports.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reports.domain.entities.ThreadReport;
import ichop.reports.domain.models.service.ThreadReportServiceModel;
import ichop.reports.repositories.ThreadReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadReportServicesImp
        extends AbstractReportServices<ThreadReport, ThreadReportServiceModel, ThreadReportRepository>
        implements ThreadReportServices {


    @Autowired
    public ThreadReportServicesImp(ObjectMapper objectMapper, ThreadReportRepository repository) {
        super(objectMapper, repository);
    }


    @Override
    public boolean existsByThreadId(String threadId) {
        return false;
    }

    @Override
    public boolean hasReported(String userId, String threadId) {
        return super.repository.existsByUserIdAndThreadId(userId, threadId);
    }

    @Override
    public boolean deleteByThreadId(String threadId) {
        return super.repository.deleteByThreadId(threadId);
    }
}
