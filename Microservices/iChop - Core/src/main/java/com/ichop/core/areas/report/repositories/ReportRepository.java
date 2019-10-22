package com.ichop.core.areas.report.repositories;

import com.ichop.core.areas.report.domain.entities.BaseReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface ReportRepository<T extends BaseReport> extends JpaRepository<T,String> {

    @Transactional
    @Modifying
    @Query("delete from #{#entityName} as r where id = :id")
    void deleteById(@Param("id") String id);

}
