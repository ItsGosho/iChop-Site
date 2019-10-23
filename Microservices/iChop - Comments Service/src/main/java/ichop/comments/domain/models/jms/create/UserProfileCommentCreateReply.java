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
public class UserProfileCommentCreateReply extends BaseReplyModel {


    private String id;
    private String content;
    private String creatorId;
    private String userProfileId;
    private LocalDateTime createdOn;
}