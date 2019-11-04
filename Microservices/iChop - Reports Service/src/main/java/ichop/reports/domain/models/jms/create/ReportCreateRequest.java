package ichop.reports.domain.models.jms.create;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class ReportCreateRequest extends RequestCandidate {

    @NotNull
    private String userId;

    @NotNull
    @NotEmpty
    private String reason;


    /*TODO: user has not created any reports for a provided thread or for comment with type*/

}
