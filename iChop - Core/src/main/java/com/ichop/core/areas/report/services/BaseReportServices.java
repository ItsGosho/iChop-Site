package com.ichop.core.areas.report.services;

import com.ichop.core.areas.report.domain.entities.BaseReport;
import com.ichop.core.areas.report.repositories.ReportRepository;
import com.ichop.core.base.BaseService;
import org.modelmapper.ModelMapper;

public abstract class BaseReportServices<Entity extends BaseReport,Repository extends ReportRepository<Entity>> extends BaseService<Entity, Repository> {


    public BaseReportServices(ModelMapper modelMapper, Repository repository) {
        super(modelMapper, repository);
    }
}
