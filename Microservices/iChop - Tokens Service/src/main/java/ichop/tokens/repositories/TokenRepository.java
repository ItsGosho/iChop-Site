package ichop.tokens.repositories;

import org.ichop.commons.domain.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;

@NoRepositoryBean
public interface TokenRepository<E extends BaseEntity> extends MongoRepository<E, String> {

    E findByUserUsername(String username);

    E findByToken(String token);

    boolean existsByToken(String token);

    Long deleteByToken(String token);
}
