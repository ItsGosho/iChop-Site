package ichop.reports.domain.models.jms;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IsUserReportedRequest extends RequestCandidate {

    @NotNull
    private Type type;

    @NotNull
    private String entityId;

    @NotNull
    private String creatorUsername;
}
