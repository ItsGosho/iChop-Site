package ichop.domain.models.service.report;

import ichop.domain.models.service.BaseServiceModel;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentReportServiceModel extends BaseServiceModel {

    private UserServiceModel user;
    private LocalDateTime reportDate;
    private String reason;
    private CommentServiceModel comment;

}
