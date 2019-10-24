package ichop.core.areas.token.repositories;

import ichop.core.areas.token.domain.entities.PasswordResetToken;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends TokenRepository<PasswordResetToken> {

}
