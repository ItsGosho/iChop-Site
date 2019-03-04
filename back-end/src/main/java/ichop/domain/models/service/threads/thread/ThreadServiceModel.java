package ichop.domain.models.service.threads.thread;

import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ThreadServiceModel {

    private String id;
    private String title;
    private String content;
    private UserServiceModel creator;
    private LocalDateTime createdOn;
    private List<CommentServiceModel> comments;
    private Integer views;
    private List<ThreadReactionServiceModel> reactions;

}
