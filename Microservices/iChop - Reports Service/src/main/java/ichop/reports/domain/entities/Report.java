package ichop.reports.domain.entities;

import ichop.reports.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.MongoEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Document("reports")
public class Report extends MongoEntity {

    @NotNull
    private String creatorUsername;

    @NotNull
    private String reason;

    @NotNull
    private Type type;

    @NotNull
    private String entityId;

    private LocalDateTime reportedOn = LocalDateTime.now();

}
