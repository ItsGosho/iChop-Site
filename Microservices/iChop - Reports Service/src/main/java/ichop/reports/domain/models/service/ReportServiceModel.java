package ichop.reports.domain.models.service;

import ichop.reports.common.domain.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class ReportServiceModel extends BaseServiceModel {

    private String userId;
    private String reason;
    private LocalDateTime reportedOn;

}
