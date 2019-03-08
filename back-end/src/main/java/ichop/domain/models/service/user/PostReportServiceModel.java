package ichop.domain.models.service.user;

import ichop.domain.entities.users.post.Post;
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
    private PostReportServiceModel post;

}
