package ichop.reports.domain.models.jms.all.pageable.request;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentReportsAllPageableRequest extends ReportAllPageableRequest {

    @NotNull
    private Type type;

}
