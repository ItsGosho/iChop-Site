package ichop.comments.domain.models.jms.create;

import ichop.comments.common.domain.BaseReplyModel;
import ichop.comments.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfileCommentCreateReply extends CommentCreateReply {

    private String userProfileUsername;

}
