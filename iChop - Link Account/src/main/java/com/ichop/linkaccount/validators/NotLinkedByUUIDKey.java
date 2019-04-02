package com.ichop.linkaccount.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.ichop.linkaccount.constants.ValidationMessages.ALREADY_LINKED_BY_UUID;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NotLinkedByUUIDKeyValidator.class)
@Documented
public @interface NotLinkedByUUIDKey {

    String message() default ALREADY_LINKED_BY_UUID;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}