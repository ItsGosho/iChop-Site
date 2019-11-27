package ichop.core.areas.report.models.jms;

import ichop.core.areas.report.models.ReportOn;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.ReplyCandidate;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportReply extends ReplyCandidate {

    private String id;
    private String creatorUsername;
    private String reason;
    private LocalDateTime reportedOn;
    private String entityId;
    private ReportOn type;
}
