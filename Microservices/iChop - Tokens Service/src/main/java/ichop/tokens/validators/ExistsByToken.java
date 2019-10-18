package ichop.tokens.validators;

import ichop.tokens.domain.enums.TokenType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsByTokenValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByToken {

    TokenType type();
    String message() default "Token doesn't exist!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
