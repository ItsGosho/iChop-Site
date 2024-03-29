package com.ichop.basicstatistics.services;

import com.ichop.basicstatistics.domain.entities.PlayerStatistics;
import com.ichop.basicstatistics.domain.models.service.PlayerStatisticsServiceModel;
import com.ichop.basicstatistics.repositories.PlayerStatisticsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatisticsServicesImp extends BaseService<PlayerStatistics, PlayerStatisticsRepository> implements PlayerStatisticsServices {


    @Autowired
    public PlayerStatisticsServicesImp(ModelMapper modelMapper, PlayerStatisticsRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public PlayerStatisticsServiceModel findByUUID(String uuid) {

        if (!this.existsByUUID(uuid)) {
            return null;
        }

        PlayerStatistics playerStatistics = this.repository.findByUuid(uuid);
        return this.modelMapper.map(playerStatistics, PlayerStatisticsServiceModel.class);
    }

    @Override
    public boolean existsByUUID(String uuid){
        return this.repository.existsByUuid(uuid);
    }

}
