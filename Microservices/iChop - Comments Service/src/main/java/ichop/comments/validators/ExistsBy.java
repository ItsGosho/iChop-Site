package ichop.comments.validators;

import ichop.comments.domain.enums.Type;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsByValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsBy {

    Type type();
    String field();
    String message() default "Whoops doesn't exists!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
