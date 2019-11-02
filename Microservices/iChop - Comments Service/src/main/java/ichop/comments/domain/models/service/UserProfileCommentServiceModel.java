package ichop.comments.domain.models.service;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserProfileCommentServiceModel extends CommentServiceModel {

    private String userProfileUsername;

}
