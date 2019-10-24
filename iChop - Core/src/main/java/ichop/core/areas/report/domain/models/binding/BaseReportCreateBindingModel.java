package ichop.core.areas.report.domain.models.binding;

import ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class BaseReportCreateBindingModel {

    @NotNull
    @NotEmpty
    private String reason;

    @NotNull
    private UserServiceModel user;

}
