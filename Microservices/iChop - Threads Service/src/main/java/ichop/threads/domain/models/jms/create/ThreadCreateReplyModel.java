package ichop.threads.domain.models.jms.create;

import ichop.threads.domain.models.jms.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadCreateReplyModel extends BaseReplyModel {

    private String id;
    private String userId;
    private String title;
    private String content;
    private LocalDateTime createdOn = LocalDateTime.now();
    private Integer views = 0;

}
