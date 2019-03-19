package ichop.service.report;

import ichop.domain.entities.report.BaseReport;
import ichop.repository.report.ReportRepository;
import ichop.service.BaseService;
import org.modelmapper.ModelMapper;

public abstract class BaseReportServices<Entity extends BaseReport,Repository extends ReportRepository<Entity>> extends BaseService<Entity, Repository> {


    public BaseReportServices(ModelMapper modelMapper, Repository repository) {
        super(modelMapper, repository);
    }
}
