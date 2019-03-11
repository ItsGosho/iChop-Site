package ichop.domain.models.service.reaction;

import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentReactionServiceModel {

    private String id;
    private UserServiceModel user;
    private ReactionType reactionType;
    private CommentServiceModel comment;
    private LocalDateTime reactionDate;
}
