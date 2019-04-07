package com.ichop.basicstatistics.services;

import com.ichop.basicstatistics.domain.entities.PlayerStatistics;
import com.ichop.basicstatistics.domain.models.service.PlayerStatisticsServiceModel;
import com.ichop.basicstatistics.repositories.PlayerStatisticsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerStatisticsServicesUnitTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PlayerStatisticsRepository playerStatisticsRepository;

    @Spy
    @InjectMocks
    private PlayerStatisticsServicesImp playerStatisticsServices;


    @Before
    public void setUp() {
    }

    @Test
    public void findByUUID_withNotExistingUUID_shouldReturnNull() {

        doReturn(false).when(this.playerStatisticsServices).existsByUUID("uuid");
        PlayerStatisticsServiceModel playerStatistics = this.playerStatisticsServices.findByUUID("uuid");

        assertNull(playerStatistics);
    }

    @Test
    public void findByUUID_withExistingUUID_shouldInvokeMethods() {
        PlayerStatistics playerStatistics = mock(PlayerStatistics.class);
        String uuid = "uuid";

        when(this.playerStatisticsRepository.findByUuid(uuid)).thenReturn(playerStatistics);
        doReturn(true).when(this.playerStatisticsServices).existsByUUID(uuid);
        this.playerStatisticsServices.findByUUID(uuid);

        verify(this.playerStatisticsRepository, times(1)).findByUuid(uuid);
        verify(this.modelMapper, times(1)).map(playerStatistics, PlayerStatisticsServiceModel.class);
    }

    @Test
    public void existsByUUID_withNotExistingPlayerStatistic_shouldReturnFalse() {
        String uuid = "uuid";
        when(this.playerStatisticsRepository.existsByUuid(uuid)).thenReturn(false);
        boolean result = this.playerStatisticsServices.existsByUUID(uuid);

        assertFalse(result);
    }

    @Test
    public void existsByUUID_withExistingPlayerStatistic_shouldInvokeMethods() {
        String uuid = "uuid";

        when(this.playerStatisticsRepository.existsByUuid(uuid)).thenReturn(true);
        boolean result = this.playerStatisticsServices.existsByUUID(uuid);

        assertTrue(result);
    }

}
