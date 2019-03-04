package ichop.domain.models.view.comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentReadThreadViewModel {

    private String id;
    private String content;
    private String creatorUsername;
    private Integer creatorTotalComments;
    private LocalDateTime createdOn;
    private Integer totalLikes;
    private Integer totalDislikes;
}
