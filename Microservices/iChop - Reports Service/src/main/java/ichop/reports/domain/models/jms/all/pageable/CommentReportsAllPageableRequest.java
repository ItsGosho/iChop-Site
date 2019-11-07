package ichop.reports.domain.models.jms.all.pageable;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentReportsAllPageableRequest extends ReportsAllPageableRequest {

    @NotNull
    private Type type;

}
