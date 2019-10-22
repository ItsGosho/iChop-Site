package ichop.reports.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Document("thread_reports")
public class ThreadReport extends BaseReport {

    @NotNull
    private String threadId;

}
