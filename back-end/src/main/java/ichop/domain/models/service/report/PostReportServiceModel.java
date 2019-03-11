package ichop.domain.models.service.report;

import ichop.domain.models.service.post.PostServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostReportServiceModel {

    private String id;
    private UserServiceModel user;
    private String reason;
    private LocalDateTime reportDate;
    private PostServiceModel post;

}
