package ichop.domain.models.service.thread;

import ichop.domain.entities.report.ThreadReport;
import ichop.domain.models.service.BaseServiceModel;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.reaction.ThreadReactionServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ThreadServiceModel extends BaseServiceModel {

    private String title;
    private String content;
    private UserServiceModel creator;
    private LocalDateTime createdOn;
    private List<CommentServiceModel> comments;
    private Integer views;
    private List<ThreadReactionServiceModel> reactions;
    private List<ThreadReport> reports;

}
