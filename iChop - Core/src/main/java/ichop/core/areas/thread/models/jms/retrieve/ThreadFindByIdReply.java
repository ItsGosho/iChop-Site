package ichop.core.areas.thread.models.jms.retrieve;


import ichop.core.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

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
