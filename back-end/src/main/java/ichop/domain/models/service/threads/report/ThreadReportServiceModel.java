package ichop.domain.models.service.threads.report;

import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadReportServiceModel {

    private String id;
    private UserServiceModel user;
    private LocalDateTime reportDate;
    private String reason;
    private ThreadServiceModel thread;
}
