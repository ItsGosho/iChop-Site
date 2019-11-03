package ichop.CHANGE.common.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class SpELValidationValidator implements ConstraintValidator<SpELValidation, Object> {

    private SpELValidation annotation;
    private ExpressionParser parser;

    private final ApplicationContext applicationContext;

    @Autowired
    public SpELValidationValidator(ApplicationContext applicationContext) {
        this.parser = new SpelExpressionParser();
        this.applicationContext = applicationContext;
    }

    public void initialize(SpELValidation constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        StandardEvaluationContext spelContext = this.getSpELContext(value);
        boolean result = (boolean) this.parser.parseExpression(this.annotation.value()).getValue(spelContext);

        return result;
    }

    private StandardEvaluationContext getSpELContext(Object value) {
        AutowireCapableBeanFactory beanFactory = this.applicationContext.getAutowireCapableBeanFactory();
        BeanFactoryResolver beanResolver = new BeanFactoryResolver(beanFactory);

        StandardEvaluationContext spELContext = new StandardEvaluationContext(value);
        spELContext.setBeanResolver(beanResolver);

        return spELContext;
    }

}