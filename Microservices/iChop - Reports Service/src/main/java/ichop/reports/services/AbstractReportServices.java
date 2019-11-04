package ichop.reports.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reports.domain.entities.Report;
import ichop.reports.domain.models.service.ReportServiceModel;
import ichop.reports.repositories.ReportRepository;
import org.ichop.commons.service.BaseMongoService;

public abstract class AbstractReportServices
        <E extends Report, S extends ReportServiceModel, R extends ReportRepository<E>>
        extends BaseMongoService<E, S, R>
        implements ReportServices<S> {

    public AbstractReportServices(ObjectMapper objectMapper, R repository) {
        super(objectMapper, repository);
    }



}
