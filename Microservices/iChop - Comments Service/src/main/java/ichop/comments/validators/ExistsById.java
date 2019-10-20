package ichop.comments.validators;

import ichop.comments.domain.enums.Type;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsByTokenValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsById {

    Type type();
    String message() default "Comment doesn't exist!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
