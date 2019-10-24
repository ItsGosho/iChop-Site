package ichop.core.areas.report.services;

import ichop.core.areas.report.domain.entities.BaseReport;
import ichop.core.areas.report.repositories.ReportRepository;
import ichop.core.base.BaseService;
import org.modelmapper.ModelMapper;

public abstract class BaseReportServices<Entity extends BaseReport,Repository extends ReportRepository<Entity>> extends BaseService<Entity, Repository> {


    public BaseReportServices(ModelMapper modelMapper, Repository repository) {
        super(modelMapper, repository);
    }

}
