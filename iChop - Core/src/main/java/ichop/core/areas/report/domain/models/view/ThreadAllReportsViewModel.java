package ichop.core.areas.report.domain.models.view;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadAllReportsViewModel {

    private String threadId;
    private String reason;
    private String creatorUsername;
    private LocalDateTime reportDate;
    private String reportId;

}
