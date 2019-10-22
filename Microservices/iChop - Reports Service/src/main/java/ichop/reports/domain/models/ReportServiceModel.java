package ichop.reports.domain.models;

import ichop.reports.common.domain.BaseEntity;
import ichop.reports.common.domain.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class ReportServiceModel extends BaseServiceModel {

    private String userId;
    private String reason;
    private LocalDateTime reportedOn;

}
