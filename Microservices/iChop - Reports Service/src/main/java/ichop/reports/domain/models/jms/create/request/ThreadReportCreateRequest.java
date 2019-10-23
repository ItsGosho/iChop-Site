package ichop.reports.domain.models.jms.create.request;

import ichop.reports.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@threadReportServicesImp.hasReported(#this.userId,#this.threadId) == false",message = "You have already reported that thread!")
public class ThreadReportCreateRequest extends ReportCreateRequest {

    @NotNull
    private String threadId;

}
