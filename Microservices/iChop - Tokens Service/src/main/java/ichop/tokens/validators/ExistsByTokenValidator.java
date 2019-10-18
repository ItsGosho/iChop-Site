package ichop.tokens.validators;

import ichop.tokens.domain.enums.TokenType;
import ichop.tokens.services.PasswordTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ExistsByTokenValidator implements ConstraintValidator<ExistsByToken, String> {

    private ExistsByToken annotation;
    private final PasswordTokenServices passwordTokenServices;

    @Autowired
    public ExistsByTokenValidator(PasswordTokenServices passwordTokenServices) {
        this.passwordTokenServices = passwordTokenServices;
    }

    @Override
    public void initialize(ExistsByToken annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String token, ConstraintValidatorContext constraintValidatorContext) {
        TokenType tokenType = this.annotation.type();

        switch (tokenType) {
            case PASSWORD:
                return this.passwordTokenServices.existsByToken(token);
            default:
                return false;
        }
    }

}