package ichop.comments.domain.models.jms.delete;

import ichop.comments.common.domain.BaseRequestModel;
import ichop.comments.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@threadCommentServicesImp.existsById(#this.id) == true", message = "Comment doesn't exist!")
public class UserProfileCommentDeleteByIdRequest extends BaseRequestModel {

    @NotNull
    private String id;
}
