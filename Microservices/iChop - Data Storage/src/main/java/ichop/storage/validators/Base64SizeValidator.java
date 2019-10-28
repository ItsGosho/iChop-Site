package ichop.storage.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64SizeValidator implements ConstraintValidator<Base64Size, String> {

    private Double minInMB;
    private Double maxInMB;

    @Override
    public void initialize(Base64Size constraintAnnotation) {
        this.minInMB = constraintAnnotation.minInMB();
        this.maxInMB = constraintAnnotation.maxInMB();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value.equals("")) {
            return true;
        }

        double sizeInMb = this.getSizeInMB(value);

        return sizeInMb > this.minInMB || sizeInMb < this.maxInMB;
    }

    private Double getSizeInMB(String base64) {
        int y = base64.endsWith("==") ? 2 : 1;
        return (((base64.length() * 3) / 4 - y) / 1024.00) / 1024.00;
    }

}
