package ichop.reports.domain.models.jms.all.pageable;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class ReportsAllPageableRequest extends RequestCandidate {

    @NotNull
    private Pageable pageable;

}
