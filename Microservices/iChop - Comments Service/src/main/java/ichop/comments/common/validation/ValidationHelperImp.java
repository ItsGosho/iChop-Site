package ichop.comments.common.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidationHelperImp implements ValidationHelper {

    private final Validator validator;


    public ValidationHelperImp() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean isValid(Object object) {
        Set<ConstraintViolation<Object>> errors = this.validator.validate(object);

        if (errors.size() == 0) {
            return true;
        }

        return false;
    }

    @Override
    public String getValidationError(Object object) {
        Set<ConstraintViolation<Object>> errors = this.validator.validate(object);
        ConstraintViolation<Object> validationError = errors.stream().findFirst().orElse(null);

        return validationError.getMessage();
    }
}
