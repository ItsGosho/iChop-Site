package ichop.storage.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = Base64ImageValidator.class)
@Documented
public @interface Base64Image {

    String message() default "Failed validation of image";

    int maxHeight() default 50;
    int maxWidth() default 50;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
