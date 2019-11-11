package ichop.reactions.helpers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

public interface CriteriaHelper {

    Query createBy(Pageable pageable, Object object);

    Query createBy(Object object);
}