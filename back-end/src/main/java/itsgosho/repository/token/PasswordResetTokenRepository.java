package itsgosho.repository.token;

import itsgosho.domain.entities.tokens.PasswordResetToken;
import itsgosho.domain.entities.users.User;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends TokenRepository<PasswordResetToken> {

}
