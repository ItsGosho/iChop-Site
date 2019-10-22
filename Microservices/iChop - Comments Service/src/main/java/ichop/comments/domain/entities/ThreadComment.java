package ichop.comments.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadComment extends Comment {

    @NotNull
    private String threadId;
}

