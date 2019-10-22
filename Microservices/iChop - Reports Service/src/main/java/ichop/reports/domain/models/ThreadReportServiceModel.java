package ichop.reports.domain.models;

import ichop.reports.domain.entities.Report;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadReportServiceModel extends ReportServiceModel {

    private String threadId;

}
