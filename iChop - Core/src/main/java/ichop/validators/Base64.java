package ichop.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = Base64Validator.class)
@Documented
public @interface Base64 {

    String message() default "Its not base64";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
