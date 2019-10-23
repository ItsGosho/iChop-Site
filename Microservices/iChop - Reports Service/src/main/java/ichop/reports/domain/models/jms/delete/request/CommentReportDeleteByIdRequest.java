package ichop.reports.domain.models.jms.delete.request;

import ichop.reports.common.domain.BaseRequestModel;
import ichop.reports.domain.enums.Type;
import ichop.reports.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@commentReportServicesImp.exists(#this.commentId,#this.type)",message = "Report doesn't exist!")
public class CommentReportDeleteByIdRequest extends BaseRequestModel {

    @NotNull
    private String commentId;

    @NotNull
    private Type type;

}
