package ichop.service.report;

import ichop.domain.models.service.report.CommentReportServiceModel;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.report.ThreadReportServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.report.PostReportServiceModel;
import ichop.domain.models.service.post.PostServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.post.PostNotFoundException;
import ichop.exceptions.thread.CommentNotFoundException;
import ichop.service.report.crud.ReportCrudServices;
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

    @Override
    public PostReportServiceModel addReport(PostServiceModel post, UserServiceModel user, String reason) {

        if(post == null){
            throw new PostNotFoundException();
        }

        PostReportServiceModel postReport = new PostReportServiceModel();
        postReport.setPost(post);
        postReport.setUser(user);
        postReport.setReason(reason);
        postReport.setReportDate(LocalDateTime.now());

        PostReportServiceModel result = this.reportCrudServices.save(postReport);

        return result;
    }
}
