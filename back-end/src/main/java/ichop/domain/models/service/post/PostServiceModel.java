package ichop.domain.models.service.post;

import ichop.domain.entities.users.User;
import ichop.domain.models.service.report.PostReportServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostServiceModel {

    private String id;
    private UserServiceModel user;
    private UserServiceModel creator;
    private String content;
    private LocalDateTime createdOn;
    private List<PostReportServiceModel> reports;

}
