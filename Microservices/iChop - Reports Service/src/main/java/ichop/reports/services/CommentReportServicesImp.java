package ichop.reports.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reports.domain.entities.CommentReport;
import ichop.reports.domain.enums.Type;
import ichop.reports.domain.models.service.CommentReportServiceModel;
import ichop.reports.repositories.CommentReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return super.repository.existsByCommentIdAndType(commentId, type);
    }

    @Override
    public boolean deleteByCommentId(String commentId, Type type) {
        return super.repository.deleteByCommentIdAndType(commentId, type);
    }

    @Override
    public <M> List<M> findAll(Pageable pageable, Type type, Class<M> returnModelClass) {
        return super.findAll(pageable)
                .stream()
                .filter(x -> x.getType().equals(type))
                .map(x -> super.objectMapper.convertValue(x, returnModelClass))
                .collect(Collectors.toList());
    }
}
