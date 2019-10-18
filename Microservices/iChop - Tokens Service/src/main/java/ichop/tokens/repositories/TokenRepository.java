package ichop.tokens.repositories;

import ichop.tokens.common.domain.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;

@NoRepositoryBean
public interface TokenRepository<E extends BaseEntity> extends MongoRepository<E, String> {

    E findByUserId(String userId);

    E findByToken(String token);
}
