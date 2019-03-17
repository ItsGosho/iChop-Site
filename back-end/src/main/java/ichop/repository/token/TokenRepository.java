package ichop.repository.token;

import ichop.domain.entities.tokens.BaseToken;
import ichop.domain.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TokenRepository<T extends BaseToken> extends JpaRepository<T,String> {

    T findByUser(User user);
    T findByToken(String token);
}
