package ichop.reactions.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@SuppressWarnings("all")
public class CriteriaHelperImp implements CriteriaHelper {

    private final ObjectMapper objectMapper;

    @Autowired
    public CriteriaHelperImp(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Query createBy(Pageable pageable, Object object) {
        Query query = new Query().with(pageable);

        return this.proceedQueryCreation(query, object);
    }

    @Override
    public Query createBy(Object object) {
        Query query = new Query();

        return this.proceedQueryCreation(query, object);
    }

    private Query proceedQueryCreation(Query query, Object object) {
        Map<String, Object> criterias = this.objectMapper.convertValue(object, Map.class);

        for (Map.Entry<String, Object> criteria : criterias.entrySet()) {
            if (criteria.getValue() != null) {
                query.addCriteria(Criteria.where(criteria.getKey()).is(String.valueOf(criteria.getValue())));
            }
        }

        return query;
    }
}