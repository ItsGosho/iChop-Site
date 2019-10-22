package ichop.reports.domain.models.jms.create.request;

import ichop.reports.domain.enums.Type;
import ichop.reports.validators.ValidateClassExpression;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ValidateClassExpression(value = "#this.commentId == 'joreto'")
public class CommentReportCreateRequest extends ReportCreateRequest {

    @NotNull
    private String commentId;

    @NotNull
    private Type type;

}
