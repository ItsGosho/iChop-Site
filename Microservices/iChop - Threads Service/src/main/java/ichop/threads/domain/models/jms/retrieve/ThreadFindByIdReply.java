package ichop.threads.domain.models.jms.retrieve;


import ichop.threads.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadFindByIdReply extends BaseReplyModel {

    private String id;
    private String userId;
    private String title;
    private String content;
    private LocalDateTime createdOn;
    private Integer views;
}
