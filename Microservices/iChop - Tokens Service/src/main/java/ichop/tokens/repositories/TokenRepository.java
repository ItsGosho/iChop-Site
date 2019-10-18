package ichop.tokens.repositories;

import ichop.tokens.common.domain.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TokenRepository<E extends BaseEntity> extends MongoRepository<E,String> {
}
