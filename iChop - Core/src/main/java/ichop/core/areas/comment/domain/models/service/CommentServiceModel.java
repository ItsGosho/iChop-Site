package ichop.core.areas.comment.domain.models.service;

import ichop.core.base.BaseServiceModel;
import ichop.core.areas.reaction.domain.models.service.CommentReactionServiceModel;
import ichop.core.areas.report.domain.models.service.CommentReportServiceModel;
import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CommentServiceModel extends BaseServiceModel {

    public ThreadServiceModel thread;
    public String content;
    public UserServiceModel creator;
    public LocalDateTime createdOn;
    private List<CommentReactionServiceModel> reactions;
    private List<CommentReportServiceModel> reports;

}
