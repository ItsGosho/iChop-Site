package ichop.reactions.common.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SpELValidationValidator.class})
@Repeatable(value = SpELValidation.List.class)
public @interface SpELValidation {

    String message() default "Message wasn't set!";
    String value();

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    @interface List {

        SpELValidation[] value();

    }

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}