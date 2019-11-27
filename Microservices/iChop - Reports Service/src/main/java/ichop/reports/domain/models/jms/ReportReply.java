package ichop.reports.domain.models.jms;

import ichop.reports.domain.enums.Type;
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
    private Type type;
}
