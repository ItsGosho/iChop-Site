package ichop.validators;

import org.apache.commons.io.IOUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.io.InputStream;

public class Base64SizeValidator implements ConstraintValidator<Base64Size,String> {

    private Double minInMB;
    private Double maxInMB;

    @Override
    public void initialize(Base64Size constraintAnnotation) {
        this.minInMB = constraintAnnotation.minInMB();
        this.maxInMB = constraintAnnotation.maxInMB();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value.equals("")){
            return true;
        }

        InputStream inputStream = IOUtils.toInputStream(value);
        double sizeInMb = this.getSizeInMB(inputStream);

        return sizeInMb > this.minInMB || sizeInMb < this.maxInMB;
    }

    private double getSizeInMB(InputStream inputStream){
        try {
            return inputStream.available()/1048576.00;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
