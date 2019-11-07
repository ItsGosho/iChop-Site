package ichop.reports.domain.models.service;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseServiceModel;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class ReportServiceModel extends BaseServiceModel {

    private String userId;
    private String reason;
    private LocalDateTime reportedOn;

}
