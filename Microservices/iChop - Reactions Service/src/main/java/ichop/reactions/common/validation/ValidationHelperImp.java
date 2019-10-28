package ichop.reactions.common.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
public class ValidationHelperImp implements ValidationHelper {

    private final LocalValidatorFactoryBean validator;

    @Autowired
    public ValidationHelperImp(LocalValidatorFactoryBean validator) {
        this.validator = validator;
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
