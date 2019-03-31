package com.ichop.linkaccount.validators;

import com.ichop.linkaccount.constants.ValidationMessages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.ichop.linkaccount.constants.ValidationMessages.EXISTING_SITE_USER_USERNAME;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ExistingSiteUserUsernameValidator.class)
@Documented
public @interface ExistingSiteUserUsername {

    String message() default EXISTING_SITE_USER_USERNAME;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}