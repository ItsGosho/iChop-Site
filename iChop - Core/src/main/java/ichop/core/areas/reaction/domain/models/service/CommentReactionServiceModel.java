package ichop.core.areas.reaction.domain.models.service;

import ichop.core.areas.reaction.domain.entities.ReactionType;
import ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import ichop.core.base.BaseServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentReactionServiceModel extends BaseServiceModel {

    private UserServiceModel user;
    private ReactionType reactionType;
    private CommentServiceModel comment;
    private LocalDateTime reactionDate;
}
