package ichop.tokens.repositories;

import ichop.tokens.domain.entities.PasswordToken;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordTokenRepository extends TokenRepository<PasswordToken> {

    boolean deleteAllByUserId(String userId);

}
