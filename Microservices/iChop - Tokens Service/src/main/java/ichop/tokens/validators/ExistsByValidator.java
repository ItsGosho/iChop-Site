package ichop.tokens.validators;

import ichop.tokens.domain.entities.PasswordToken;
import ichop.tokens.domain.enums.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ExistsByValidator implements ConstraintValidator<ExistsBy, String> {

    private ExistsBy annotation;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ExistsByValidator(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void initialize(ExistsBy annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        Type type = this.annotation.type();
        String fieldName = this.annotation.field();

        Query query = new Query();
        query.addCriteria(Criteria.where(fieldName).exists(true));

        switch (type) {
            case PASSWORD:
                return this.mongoTemplate.exists(query, PasswordToken.class);
            default:
                return false;
        }
    }

}