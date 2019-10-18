package ichop.threads.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsByIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsById {

    String message() default "Thread doesn't exist!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
