package ichop.threads.validators;

import ichop.threads.domain.entities.Thread;
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
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        String fieldName = this.annotation.field();

        Query query = new Query();
        query.addCriteria(Criteria.where(fieldName).exists(true));
        query.addCriteria(Criteria.where(fieldName).is(value));

        return this.mongoTemplate.exists(query, Thread.class);
    }

}