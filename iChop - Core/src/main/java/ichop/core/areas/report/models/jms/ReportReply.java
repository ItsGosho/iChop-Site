package ichop.core.areas.report.models.jms;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.ReplyCandidate;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class ReportReply extends ReplyCandidate {

    private String id;
    private String userId;
    private String reason;
    private LocalDateTime reportedOn;

}
