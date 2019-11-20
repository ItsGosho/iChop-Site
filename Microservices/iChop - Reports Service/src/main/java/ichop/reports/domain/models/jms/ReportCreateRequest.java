package ichop.reports.domain.models.jms;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@reportServicesImp.hasReported(#this.type,#this.userId,#this.entityId) == false", message = "You reported already!")
public class ReportCreateRequest extends RequestCandidate {

    @NotNull
    private String userId;

    @NotNull
    @NotEmpty
    private String reason;

    @NotNull
    private Type type;

    @NotNull
    @NotEmpty
    private String entityId;

}
