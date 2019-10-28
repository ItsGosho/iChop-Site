package ichop.storage.validators;

import javax.imageio.ImageIO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Base64ImageValidator implements ConstraintValidator<Base64Image,String> {

    private Integer maxHeight;
    private Integer maxWidth;

    @Override
    public void initialize(Base64Image constraintAnnotation) {
           this.maxHeight = constraintAnnotation.maxHeight();
           this.maxWidth = constraintAnnotation.maxWidth();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value.equals("")){
            return true;
        }

        BufferedImage bufferedImage = null;

        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(value)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(bufferedImage == null){
            return false;
        }

        if(bufferedImage.getHeight() > this.maxHeight || bufferedImage.getWidth() > this.maxWidth){
            return false;
        }

        return true;
    }


}
