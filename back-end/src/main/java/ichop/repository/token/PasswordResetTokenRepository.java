package ichop.repository.token;

import ichop.domain.entities.tokens.PasswordResetToken;
import ichop.repository.base.TokenRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends TokenRepository<PasswordResetToken> {

}
