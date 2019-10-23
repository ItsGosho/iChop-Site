package ichop.reports.domain.models.jms.create.request;

import ichop.reports.domain.enums.Type;
import ichop.reports.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@commentReportServicesImp.hasReported(#this.userId,#this.commentId,#this.type) == false",message = "You have already reported that comment!")
public class CommentReportCreateRequest extends ReportCreateRequest {

    @NotNull
    private String commentId;

    @NotNull
    private Type type;

}
