package com.ichop.core.components.email;

import com.ichop.core.components.email.dtos.ReportsStatisticsDto;

import java.time.LocalDateTime;

public interface EmailServices {

    void sendResetPasswordEmail(String userEmail, String token, LocalDateTime tokenExpireDate);

    void sendReportStatisticsEmail(ReportsStatisticsDto reportsStatisticsDto);

}
