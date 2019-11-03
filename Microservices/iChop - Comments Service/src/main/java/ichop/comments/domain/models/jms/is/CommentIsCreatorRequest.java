package ichop.comments.domain.models.jms.is;

import ichop.comments.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@genericCommentServices.existsById(#this.id,#this.type) == true", message = "Comment doesn't exist!")
public class CommentIsCreatorRequest extends RequestCandidate {

    @NotNull
    private String id;

    @NotNull
    private String creatorUsername;

    @NotNull
    private Type type;
}
