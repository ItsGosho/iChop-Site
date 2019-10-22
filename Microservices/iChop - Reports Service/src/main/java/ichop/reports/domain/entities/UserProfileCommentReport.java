package ichop.reports.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("user_profile_comment_reports")
public class UserProfileCommentReport extends CommentReport {
}
