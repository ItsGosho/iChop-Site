package ichop.core.areas.comment.domain.models.binding;

import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentCreateBindingModel {

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    private ThreadServiceModel thread;

    @NotNull
    private UserServiceModel creator;
}
