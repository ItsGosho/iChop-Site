package ichop.reports.domain.models.jms.create.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadReportCreateRequest extends ReportCreateRequest {

    @NotNull
    private String threadId;

}
