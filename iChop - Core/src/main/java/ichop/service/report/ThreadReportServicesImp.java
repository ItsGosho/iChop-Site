package ichop.service.report;

import ichop.domain.entities.report.ThreadReport;
import ichop.domain.models.service.report.ThreadReportServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.repository.report.ThreadReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ThreadReportServicesImp extends BaseReportServices<ThreadReport, ThreadReportRepository> implements ThreadReportServices {


    @Autowired
    public ThreadReportServicesImp(ModelMapper modelMapper, ThreadReportRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public ThreadReportServiceModel createReport(ThreadServiceModel thread, UserServiceModel user, String reason) {
        ThreadReportServiceModel threadReport = new ThreadReportServiceModel();
        threadReport.setThread(thread);
        threadReport.setUser(user);
        threadReport.setReason(reason);
        threadReport.setReportDate(LocalDateTime.now());

        ThreadReportServiceModel result = super.save(threadReport,ThreadReportServiceModel.class);

        return result;
    }
}
