package com.ichop.core.areas.report.repositories;

import com.ichop.core.areas.report.domain.entities.BaseReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReportRepository<T extends BaseReport> extends JpaRepository<T,String> {

}
