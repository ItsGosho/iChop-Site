package ichop.threads.domain.models.jms.create;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseReplyModel;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadCreateReply extends BaseReplyModel {

    private String id;
    private String creatorUsername;
    private String title;
    private String content;
    private LocalDateTime createdOn = LocalDateTime.now();
    private Integer views = 0;

}
