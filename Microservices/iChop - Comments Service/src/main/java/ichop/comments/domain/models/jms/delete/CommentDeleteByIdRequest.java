package ichop.comments.domain.models.jms.delete;

import ichop.comments.domain.enums.Type;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@genericCommentServices.existsById(#this.id,#this.type) == true", message = "Comment doesn't exist!")
public class CommentDeleteByIdRequest extends RequestCandidate {

    @NotNull
    private String id;

    @NotNull
    private Type type;
}