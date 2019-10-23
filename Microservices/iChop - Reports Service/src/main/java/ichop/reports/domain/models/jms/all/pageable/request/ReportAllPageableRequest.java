package ichop.reports.domain.models.jms.all.pageable.request;

import ichop.reports.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class ReportAllPageableRequest extends BaseRequestModel {

    @NotNull
    private Pageable pageable;

}
