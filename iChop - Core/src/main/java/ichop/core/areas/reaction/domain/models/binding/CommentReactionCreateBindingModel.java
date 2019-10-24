package ichop.core.areas.reaction.domain.models.binding;

import ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import ichop.core.areas.reaction.domain.entities.ReactionType;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReactionCreateBindingModel {

    private UserServiceModel user;
    private CommentServiceModel comment;
    private ReactionType reactionType;

}
