package ichop.reports.domain.models.jms.delete.request;

import ichop.reports.common.domain.BaseRequestModel;
import ichop.reports.domain.enums.Type;
import ichop.reports.validators.ExistsBy;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadReportDeleteByIdRequest extends BaseRequestModel {

    @NotNull
    @ExistsBy(type = Type.THREAD,field = "threadId",message = "Report doesn't exist!")
    private String threadId;

}
