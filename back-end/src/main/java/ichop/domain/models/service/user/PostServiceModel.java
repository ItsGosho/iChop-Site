package ichop.domain.models.service.user;

import ichop.domain.entities.users.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostServiceModel {

    private String id;
    private UserServiceModel user;
    private UserServiceModel creator;
    private String content;
    private LocalDateTime createdOn;

}
