package ichop.tokens.repositories;

import ichop.tokens.domain.entities.PasswordToken;
import org.springframework.stereotype.Repository;


@Repository
public interface PasswordTokenRepository extends TokenRepository<PasswordToken> {

    Long deleteAllByUserUsername(String username);

}
