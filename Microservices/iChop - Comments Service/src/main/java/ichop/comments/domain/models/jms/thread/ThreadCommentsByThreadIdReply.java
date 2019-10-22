package ichop.comments.domain.models.jms.thread;

import ichop.comments.common.domain.BaseReplyModel;
import ichop.comments.domain.models.service.ThreadCommentServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ThreadCommentsByThreadIdReply extends BaseReplyModel {

    private List<ThreadCommentServiceModel> comments;

    public ThreadCommentsByThreadIdReply(String message, List<ThreadCommentServiceModel> comments) {
        super(message);
        this.comments = comments;
    }
}
