package ichop.domain.models.service.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostServiceModel {

    private String id;
    private UserServiceModel user;
    private String content;
    private LocalDateTime createdOn;

}
