package itsgosho.validators.fields;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

///*
// @Author ItsGosho
// @Usage
// -> The provided email must not exists in the database
// -> NOTE: The email column must be UNIQUE!
// */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsMustMatchConstraint.class)
public @interface FieldsMustMatch {

    String field1() default "";
    String field2() default "";
    String message() default "The fields doesnt't match!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
