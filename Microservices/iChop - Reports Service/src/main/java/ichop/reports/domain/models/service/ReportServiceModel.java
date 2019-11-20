package ichop.reports.domain.models.service;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseServiceModel;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportServiceModel extends BaseServiceModel {

    private String userId;
    private String reason;
    private LocalDateTime reportedOn;
    private String entityId;
    private Type type;

}
