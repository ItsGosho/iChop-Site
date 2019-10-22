package ichop.reports.domain.entities;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Document("comment_reports")
public class CommentReport extends Report {

    @NotNull
    private String commentId;

    @NotNull
    private Type type;

}
