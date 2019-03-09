package ichop.domain.models.view.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostProfileViewModel {

    private String id;
    private String content;
    private String creatorUsername;
    private LocalDateTime createdOn;

}
