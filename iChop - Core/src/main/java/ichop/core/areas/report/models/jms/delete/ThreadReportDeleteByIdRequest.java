package ichop.core.areas.report.models.jms.delete;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadReportDeleteByIdRequest extends RequestCandidate {

    @NotNull
    private String threadId;

}
