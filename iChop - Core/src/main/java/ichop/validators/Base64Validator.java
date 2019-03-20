package ichop.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base64Validator implements ConstraintValidator<Base64,String> {

    private static final String BASE_64_PATTERN = "^([A-Za-z0-9+\\/]{4})*([A-Za-z0-9+\\/]{3}=|[A-Za-z0-9+\\/]{2}==)?$";

    @Override
    public void initialize(Base64 constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value.equals("")){
            return true;
        }

        Pattern pattern = Pattern.compile(BASE_64_PATTERN,Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(value);
        boolean isFound = matcher.find();

        return isFound;
    }
}
