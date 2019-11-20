package ichop.reports.services;

import ichop.reports.domain.enums.Type;
import ichop.reports.domain.models.service.ReportServiceModel;
import org.ichop.commons.service.BaseService;

public interface ReportServices extends BaseService<ReportServiceModel> {

    boolean existsById(Type type,String id);
    boolean hasReported(Type type, String userId, String entityId);
    void deleteById(Type type, String id);

}
