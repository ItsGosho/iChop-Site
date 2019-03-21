package com.ichop.core.repository.report;

import com.ichop.core.domain.entities.report.BaseReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReportRepository<T extends BaseReport> extends JpaRepository<T,String> {

}
