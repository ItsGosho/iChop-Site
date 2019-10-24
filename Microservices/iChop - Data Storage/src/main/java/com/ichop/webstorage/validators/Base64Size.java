package com.ichop.webstorage.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = Base64SizeValidator.class)
@Documented
public @interface Base64Size {

    String message() default "Size is not in range.";

    double minInMB() default 0.00;

    double maxInMB() default 1.00;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
