package ichop.reports.validators;


import ichop.reports.domain.entities.CommentReport;
import ichop.reports.domain.entities.ThreadReport;
import ichop.reports.domain.enums.Type;
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
        Type type = this.annotation.type();
        String fieldName = this.annotation.field();

        Query query = new Query();
        query.addCriteria(Criteria.where(fieldName).exists(true));
        query.addCriteria(Criteria.where(fieldName).is(value));

        switch (type) {
            case THREAD:
                return this.mongoTemplate.exists(query, ThreadReport.class);
            case THREAD_COMMENT:
            case USER_PROFILE_COMMENT:
                query.addCriteria(Criteria.where("type").is(type));
                return this.mongoTemplate.exists(query, CommentReport.class);
            default:
                return false;
        }
    }

}