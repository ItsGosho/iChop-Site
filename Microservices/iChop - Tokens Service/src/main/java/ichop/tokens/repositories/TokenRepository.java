package ichop.tokens.repositories;

import org.ichop.commons.domain.MongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TokenRepository<E extends MongoEntity> extends MongoRepository<E, String> {

    E findByUserUsername(String username);

    E findByToken(String token);

    boolean existsByToken(String token);

    Long deleteByToken(String token);
}
