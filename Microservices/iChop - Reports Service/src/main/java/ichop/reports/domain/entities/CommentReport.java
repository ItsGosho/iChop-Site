package ichop.reports.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class CommentReport extends Report {

    @NotNull
    private String commentId;

}
