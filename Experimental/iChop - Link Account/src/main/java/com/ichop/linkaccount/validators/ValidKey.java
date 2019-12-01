package com.ichop.linkaccount.validators;

import com.ichop.linkaccount.constants.ValidationMessages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.ichop.linkaccount.constants.ValidationMessages.KEY_NOT_VALID;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidKeyValidator.class)
@Documented
public @interface ValidKey {

    String message() default KEY_NOT_VALID;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}