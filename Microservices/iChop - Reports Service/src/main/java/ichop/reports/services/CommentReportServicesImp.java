package ichop.reports.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reports.domain.entities.CommentReport;
import ichop.reports.domain.enums.Type;
import ichop.reports.domain.models.service.CommentReportServiceModel;
import ichop.reports.repositories.CommentReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentReportServicesImp
        extends AbstractReportServices<CommentReport, CommentReportServiceModel, CommentReportRepository>
        implements CommentReportServices {


    @Autowired
    public CommentReportServicesImp(ObjectMapper objectMapper, CommentReportRepository repository) {
        super(objectMapper, repository);
    }


    @Override
    public boolean hasReported(String userId, String commentId, Type type) {
        return super.repository.existsByUserIdAndCommentIdAndType(userId, commentId, type);
    }

    @Override
    public boolean exists(String commentId, Type type) {
        return super.repository.existsByCommentIdAndType(commentId,type);
    }
}
