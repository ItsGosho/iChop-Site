package ichop.threads.domain.models.jms.retrieve;


import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseReplyModel;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadFindByIdReply extends BaseReplyModel {

    private String id;
    private String creatorUsername;
    private String title;
    private String content;
    private LocalDateTime createdOn;
    private Integer views;
}
