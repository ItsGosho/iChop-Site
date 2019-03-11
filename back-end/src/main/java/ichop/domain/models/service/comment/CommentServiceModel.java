package ichop.domain.models.service.comment;

import ichop.domain.models.service.reaction.CommentReactionServiceModel;
import ichop.domain.models.service.report.CommentReportServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CommentServiceModel {

    private String id;
    public ThreadServiceModel thread;
    public String content;
    public UserServiceModel creator;
    public LocalDateTime createdOn;
    private List<CommentReactionServiceModel> reactions;
    private List<CommentReportServiceModel> reports;

}
