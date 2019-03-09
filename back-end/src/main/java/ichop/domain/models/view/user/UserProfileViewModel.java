package ichop.domain.models.view.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfileViewModel {

    private String username;
    private String role;
    private String location;
    private LocalDateTime lastOnline;
    private LocalDateTime registrationDate;
    private Integer totalMessages;
    private Integer totalLikes;
    private Integer totalDislikes;

    //TODO:

}
