package ichop.reports.validators;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@SuppressWarnings("all")
public class ValidateClassExpressionValidator implements ConstraintValidator<ValidateClassExpression, Object> {

    private ValidateClassExpression annotation;
    private ExpressionParser parser = new SpelExpressionParser();

    public void initialize(ValidateClassExpression constraintAnnotation) {
        annotation = constraintAnnotation;
        parser.parseExpression(constraintAnnotation.value());
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        StandardEvaluationContext spelContext = new StandardEvaluationContext(value);
        boolean isValid = (Boolean) parser.parseExpression(annotation.value()).getValue(spelContext);
        return isValid;
    }

}