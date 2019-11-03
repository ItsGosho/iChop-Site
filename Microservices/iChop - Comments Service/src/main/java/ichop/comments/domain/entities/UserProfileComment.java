package ichop.comments.domain.entities;

import ichop.comments.annotations.CommentType;
import ichop.comments.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Document("user_profile_comments")
@CommentType(type = Type.USER_PROFILE)
public class UserProfileComment extends Comment {

    @NotNull
    private String userProfileUsername;

}
