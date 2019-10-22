package ichop.reports.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidateClassExpressionValidator.class})
@Documented
public @interface ValidateClassExpression {

   String message() default "";
 
   Class<?>[] groups() default { };

   Class<? extends Payload>[] payload() default { };
 
   String value();

}