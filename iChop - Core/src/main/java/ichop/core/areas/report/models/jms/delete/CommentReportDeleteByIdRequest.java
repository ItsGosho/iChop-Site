package ichop.core.areas.report.models.jms.delete;

import ichop.core.areas.report.models.ReportOn;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentReportDeleteByIdRequest extends RequestCandidate {

    @NotNull
    private String commentId;

    @NotNull
    private ReportOn type;

}
