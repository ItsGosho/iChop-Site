package ichop.comments.domain.models.jms.profile;

import ichop.comments.common.domain.BaseReplyModel;
import ichop.comments.common.domain.BaseRequestModel;
import ichop.comments.domain.models.service.UserProfileCommentServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileCommentsByUserProfileIdReply extends BaseReplyModel {

    private List<UserProfileCommentServiceModel> comments;


    public UserProfileCommentsByUserProfileIdReply(String message, List<UserProfileCommentServiceModel> comments) {
        super(message);
        this.comments = comments;
    }
}
