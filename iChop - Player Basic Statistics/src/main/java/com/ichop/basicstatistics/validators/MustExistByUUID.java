package com.ichop.basicstatistics.validators;

import com.ichop.basicstatistics.constants.ValidationMessages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = MustExistByUUIDValidator.class)
@Documented
public @interface MustExistByUUID {

    String message() default ValidationMessages.NOT_USER_EXIST_BY_UUID;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}