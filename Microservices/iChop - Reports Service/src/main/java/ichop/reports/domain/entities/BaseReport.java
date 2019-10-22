package ichop.reports.domain.entities;

import ichop.reports.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseReport extends BaseEntity {

    @NotNull
    private String userId;

    @NotNull
    private String reason;

    @NotNull
    private LocalDateTime reportedOn;

}
