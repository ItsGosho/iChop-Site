package itsgosho.repository.token;

import itsgosho.domain.entities.tokens.Token;
import itsgosho.domain.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TokenRepository<T extends Token> extends JpaRepository<T,String> {

    T findByUser(User user);
    T findByToken(String token);
}
