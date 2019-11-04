package ichop.reports.domain.models.jms.delete;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@commentReportServicesImp.exists(#this.commentId,#this.type)",message = "Report doesn't exist!")
public class CommentReportDeleteByIdRequest extends RequestCandidate {

    @NotNull
    private String commentId;

    @NotNull
    private Type type;

}
