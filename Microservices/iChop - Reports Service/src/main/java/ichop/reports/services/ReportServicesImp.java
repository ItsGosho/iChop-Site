package ichop.reports.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reports.domain.entities.Report;
import ichop.reports.domain.enums.Type;
import ichop.reports.domain.models.service.ReportServiceModel;
import ichop.reports.repositories.ReportRepository;
import org.ichop.commons.service.BaseMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServicesImp
        extends BaseMongoService<Report, ReportServiceModel, ReportRepository>
        implements ReportServices {

    @Autowired
    public ReportServicesImp(ObjectMapper objectMapper, ReportRepository repository) {
        super(objectMapper, repository);
    }


    @Override
    public boolean existsById(Type type, String id) {
        return super.repository.existsByTypeAndId(type,id);
    }

    @Override
    public boolean hasReported(Type type, String userId, String entityId) {
        return super.repository.existsByTypeAndUserIdAndEntityId(type,userId,entityId);
    }

    @Override
    public void deleteById(Type type, String id) {
       super.repository.deleteByTypeAndId(type,id);
    }
}
