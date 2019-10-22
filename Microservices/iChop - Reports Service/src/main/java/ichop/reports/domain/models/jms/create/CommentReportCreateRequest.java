package ichop.reports.domain.models.jms.create;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentReportCreateRequest extends ReportCreateRequest {

    @NotNull
    private String commentId;

    @NotNull
    private Type type;

}
