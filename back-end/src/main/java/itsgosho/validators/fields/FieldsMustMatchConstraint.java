package itsgosho.validators.fields;


import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

@Component
public class FieldsMustMatchConstraint implements ConstraintValidator<FieldsMustMatch, Object> {

    private String fieldOneName;
    private String fieldTwoName;


    @Override
    public void initialize(FieldsMustMatch constraintAnnotation) {
        fieldOneName = constraintAnnotation.field1();
        fieldTwoName = constraintAnnotation.field2();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {

        try {
            Field field1 = obj.getClass().getDeclaredField(fieldOneName);
            Field field2 = obj.getClass().getDeclaredField(fieldTwoName);
            field1.setAccessible(true);
            field2.setAccessible(true);
            Object field1Value = field1.get(obj);
            Object field2Value = field2.get(obj);

            if(field1Value.equals(field2Value)){
                return true;
            }

        } catch (Exception e) {
            return false;
        }

        return false;
    }

}
