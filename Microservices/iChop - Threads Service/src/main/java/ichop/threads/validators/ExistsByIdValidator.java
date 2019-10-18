package ichop.threads.validators;

import ichop.threads.services.ThreadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ExistsByIdValidator implements ConstraintValidator<ExistsById, String> {

    private final ThreadServices threadServices;

    @Autowired
    public ExistsByIdValidator(ThreadServices threadServices) {
        this.threadServices = threadServices;
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return this.threadServices.existsById(id);
    }

}