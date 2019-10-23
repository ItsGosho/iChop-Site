package ichop.reports.services;

import ichop.reports.domain.enums.Type;
import ichop.reports.domain.models.service.CommentReportServiceModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentReportServices extends ReportServices<CommentReportServiceModel> {

    boolean hasReported(String userId, String commentId, Type type);

    boolean exists(String commentId, Type type);

    boolean deleteByCommentId(String commentId, Type type);

    <M> List<M> findAll(Pageable pageable,Type type, Class<M> returnModelClass);
}
