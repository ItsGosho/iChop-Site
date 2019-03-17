package ichop.service.report;

import ichop.domain.entities.report.CommentReport;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.report.CommentReportServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.thread.CommentNotFoundException;
import ichop.repository.report.CommentReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentReportServicesImp extends BaseReportServices<CommentReport, CommentReportRepository> implements CommentReportServices {

    @Autowired
    public CommentReportServicesImp(ModelMapper modelMapper, CommentReportRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public CommentReportServiceModel createReport(CommentServiceModel comment, UserServiceModel user, String reason) {
        if(comment == null){
            throw new CommentNotFoundException();
        }

        CommentReportServiceModel commentReport = new CommentReportServiceModel();
        commentReport.setComment(comment);
        commentReport.setUser(user);
        commentReport.setReason(reason);
        commentReport.setReportDate(LocalDateTime.now());

        CommentReportServiceModel result = super.save(commentReport,CommentReportServiceModel.class);

        return result;
    }
}
