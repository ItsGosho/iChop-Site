package ichop.comments.domain.models.jms.delete;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@threadCommentServicesImp.existsById(#this.id) == true",message = "Comment doesn't exist!")
public class ThreadCommentDeleteByIdRequest extends RequestCandidate {

    @NotNull
    private String id;

}
