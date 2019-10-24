package ichop.core.areas.reaction.domain.models.binding;

import ichop.core.areas.reaction.domain.entities.ReactionType;
import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadReactionCreateBindingModel {

    @NotNull
    private UserServiceModel user;

    @NotNull
    private ThreadServiceModel thread;

    @NotNull
    private ReactionType reactionType;

}
