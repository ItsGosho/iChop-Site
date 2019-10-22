package ichop.reports.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("thread_comment_reports")
public class ThreadCommentReport extends CommentReport {

}
