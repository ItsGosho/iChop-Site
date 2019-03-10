package ichop.service.threads.report;

import ichop.domain.entities.threads.Comment;
import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.threads.report.CommentReport;
import ichop.domain.entities.threads.report.ThreadReport;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.threads.report.CommentReportServiceModel;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.report.ThreadReportServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.thread.CommentNotFoundException;
import ichop.service.threads.report.crud.ReportCrudServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReportServicesImp implements ReportServices {

    private final ReportCrudServices reportCrudServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ReportServicesImp(ReportCrudServices reportCrudServices, ModelMapper modelMapper) {
        this.reportCrudServices = reportCrudServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public ThreadReportServiceModel addReport(ThreadServiceModel thread, UserServiceModel user, String reason) {

        ThreadReportServiceModel threadReport = new ThreadReportServiceModel();
        threadReport.setThread(thread);
        threadReport.setUser(user);
        threadReport.setReason(reason);
        threadReport.setReportDate(LocalDateTime.now());

        ThreadReportServiceModel result = this.reportCrudServices.save(threadReport);

        return result;
    }

    @Override
    public CommentReportServiceModel addReport(CommentServiceModel comment, UserServiceModel user, String reason) {

        if(comment == null){
            throw new CommentNotFoundException();
        }

        CommentReportServiceModel commentReport = new CommentReportServiceModel();
        commentReport.setComment(comment);
        commentReport.setUser(user);
        commentReport.setReason(reason);
        commentReport.setReportDate(LocalDateTime.now());

        CommentReportServiceModel result = this.reportCrudServices.save(commentReport);

        return result;
    }
}
