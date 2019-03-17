package ichop.domain.models.service.report;

import ichop.domain.models.service.BaseServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadReportServiceModel extends BaseServiceModel {

    private UserServiceModel user;
    private LocalDateTime reportDate;
    private String reason;
    private ThreadServiceModel thread;
}
