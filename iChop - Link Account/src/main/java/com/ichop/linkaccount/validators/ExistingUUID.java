package com.ichop.linkaccount.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.ichop.linkaccount.constants.ValidationMessages.EXISTING_SITE_USER_USERNAME;
import static com.ichop.linkaccount.constants.ValidationMessages.UUID_NOT_FOUND;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ExistingUUIDValidator.class)
@Documented
public @interface ExistingUUID {

    String message() default UUID_NOT_FOUND;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}