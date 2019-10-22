package ichop.reports.domain.models.jms.create;

import ichop.reports.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class ReportCreateRequest extends BaseRequestModel {

    @NotNull
    private String userId;

    @NotNull
    @NotEmpty
    private String reason;


    /*TODO: user has not created any reports for a provided thread or for comment with type*/

}
