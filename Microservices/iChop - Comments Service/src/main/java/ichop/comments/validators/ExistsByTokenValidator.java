package ichop.comments.validators;

import ichop.comments.domain.enums.Type;
import ichop.comments.services.ThreadCommentServices;
import ichop.comments.services.UserProfileCommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ExistsByTokenValidator implements ConstraintValidator<ExistsById, String> {

    private ExistsById annotation;
    private final ThreadCommentServices threadCommentServices;
    private final UserProfileCommentServices userProfileCommentServices;

    @Autowired
    public ExistsByTokenValidator(ThreadCommentServices threadCommentServices, UserProfileCommentServices userProfileCommentServices) {
        this.threadCommentServices = threadCommentServices;
        this.userProfileCommentServices = userProfileCommentServices;
    }

    @Override
    public void initialize(ExistsById annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        Type type = this.annotation.type();

        switch (type) {
            case THREAD:
                return this.threadCommentServices.existsById(id);
            case USER_PROFILE:
                return this.userProfileCommentServices.existsById(id);
            default:
                return false;
        }
    }

}