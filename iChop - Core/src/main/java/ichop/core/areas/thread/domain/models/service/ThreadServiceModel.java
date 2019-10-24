package ichop.core.areas.thread.domain.models.service;

import ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import ichop.core.areas.reaction.domain.models.service.ThreadReactionServiceModel;
import ichop.core.areas.report.domain.models.service.ThreadReportServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.base.BaseServiceModel;
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
    private List<ThreadReportServiceModel> reports;

}
