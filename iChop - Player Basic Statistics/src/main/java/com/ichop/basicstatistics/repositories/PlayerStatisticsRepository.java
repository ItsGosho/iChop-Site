package com.ichop.basicstatistics.repositories;

import com.ichop.basicstatistics.domain.entities.PlayerStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStatisticsRepository extends JpaRepository<PlayerStatistics,Long> {

    boolean existsByUuid(String uuid);
    PlayerStatistics findByUuid(String uuid);

}
