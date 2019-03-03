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
    public ThreadReportServiceModel addReport(ThreadServiceModel threadServiceModel, UserServiceModel userServiceModel, String reason) {

        Thread thread = this.modelMapper.map(threadServiceModel,Thread.class);
        User user = this.modelMapper.map(userServiceModel,User.class);

        ThreadReport threadReport = new ThreadReport();
        threadReport.setThread(thread);
        threadReport.setUser(user);
        threadReport.setReason(reason);
        threadReport.setReportDate(LocalDateTime.now());

        ThreadReportServiceModel result = this.reportCrudServices.save(this.modelMapper.map(threadReport,ThreadReportServiceModel.class));

        return result;
    }

    @Override
    public CommentReportServiceModel addReport(CommentServiceModel commentServiceModel, UserServiceModel userServiceModel, String reason) {

        if(commentServiceModel == null){
            throw new CommentNotFoundException();
        }

        Comment comment = this.modelMapper.map(commentServiceModel,Comment.class);
        User user = this.modelMapper.map(userServiceModel,User.class);

        CommentReport commentReport = new CommentReport();
        commentReport.setComment(comment);
        commentReport.setUser(user);
        commentReport.setReason(reason);
        commentReport.setReportDate(LocalDateTime.now());

        CommentReportServiceModel result = this.reportCrudServices.save(this.modelMapper.map(commentReport,CommentReportServiceModel.class));

        return result;
    }
}
