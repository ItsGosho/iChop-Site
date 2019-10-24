package ichop.core.areas.report.domain.models.binding;

import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadReportCreateBindingModel extends BaseReportCreateBindingModel {

    @NotNull
    private ThreadServiceModel thread;

}
