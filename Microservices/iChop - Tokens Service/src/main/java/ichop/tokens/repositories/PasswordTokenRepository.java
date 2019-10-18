package ichop.tokens.repositories;

import ichop.tokens.domain.entities.PasswordToken;
import ichop.tokens.domain.models.service.PasswordTokenServiceModel;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordTokenRepository extends TokenRepository<PasswordToken> {
}
