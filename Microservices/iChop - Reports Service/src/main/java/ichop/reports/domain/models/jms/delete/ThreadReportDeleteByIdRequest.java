package ichop.reports.domain.models.jms.delete;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@threadReportServicesImp.existsByThreadId(#this.threadId) == true", message = "Thread not found")
public class ThreadReportDeleteByIdRequest extends RequestCandidate {

    @NotNull
    private String threadId;

}
