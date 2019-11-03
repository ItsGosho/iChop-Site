package ichop.core.areas.thread.models.jms;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.ReplyCandidate;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadReply extends ReplyCandidate {

    private String id;
    private String creatorUsername;
    private String title;
    private String content;
    private LocalDateTime createdOn;
    private Integer views;

}
