package ichop.reports.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.MongoEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Report extends MongoEntity {

    @NotNull
    private String userId;

    @NotNull
    private String reason;

    @NotNull
    private LocalDateTime reportedOn = LocalDateTime.now();

}
