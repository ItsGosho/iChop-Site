package ichop.threads.domain.models.jms;

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
