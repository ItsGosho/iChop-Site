package ichop.reports.domain.models.jms.create.request;

import cz.jirutka.validator.spring.SpELAssert;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELAssert(value = "@threadReportServicesImp.hasReported(#this.userId,#this.threadId) == false",message = "You have already reported that thread!")
public class ThreadReportCreateRequest extends ReportCreateRequest {

    @NotNull
    private String threadId;

}
