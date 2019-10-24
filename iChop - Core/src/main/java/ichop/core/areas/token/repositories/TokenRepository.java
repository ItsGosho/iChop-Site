package ichop.core.areas.token.repositories;

import ichop.core.areas.token.domain.entities.BaseToken;
import ichop.core.areas.user.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TokenRepository<T extends BaseToken> extends JpaRepository<T,String> {

    T findByUser(User user);
    T findByToken(String token);
}
