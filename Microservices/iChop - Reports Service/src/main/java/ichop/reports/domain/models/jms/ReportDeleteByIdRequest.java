package ichop.reports.domain.models.jms;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@reportServicesImp.existsById(#this.type,#this.entityId) == true", message = "Entity not found")
public class ReportDeleteByIdRequest extends RequestCandidate {

    @NotNull
    private Type type;

    @NotNull
    private String id;

}
