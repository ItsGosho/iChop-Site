package com.ichop.core.service.report;

import com.ichop.core.domain.entities.report.BaseReport;
import com.ichop.core.repository.report.ReportRepository;
import com.ichop.core.service.BaseService;
import org.modelmapper.ModelMapper;

public abstract class BaseReportServices<Entity extends BaseReport,Repository extends ReportRepository<Entity>> extends BaseService<Entity, Repository> {


    public BaseReportServices(ModelMapper modelMapper, Repository repository) {
        super(modelMapper, repository);
    }
}
