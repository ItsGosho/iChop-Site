package ichop.core.areas.email.services;

import ichop.core.areas.email.domain.models.ReportsStatisticsDto;

import java.time.LocalDateTime;

public interface EmailServices {

    void sendResetPasswordEmail(String userEmail, String token, LocalDateTime tokenExpireDate);

    void sendReportStatisticsEmail(ReportsStatisticsDto reportsStatisticsDto);

}
