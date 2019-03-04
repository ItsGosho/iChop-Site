package ichop.domain.models.binding.thread;

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
}
