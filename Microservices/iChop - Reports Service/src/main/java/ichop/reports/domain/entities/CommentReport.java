package ichop.reports.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CommentReport extends BaseReport {

    private String commentId;

}
