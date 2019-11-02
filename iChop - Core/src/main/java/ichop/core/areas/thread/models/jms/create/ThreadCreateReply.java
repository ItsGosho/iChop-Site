package ichop.core.areas.thread.models.jms.create;

import ichop.core.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadCreateReply extends BaseReplyModel {

    private String id;
    private String title;
    private String content;
    private LocalDateTime createdOn = LocalDateTime.now();
    private Integer views = 0;

    private String creatorUsername;
}
