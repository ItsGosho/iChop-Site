package com.ichop.core.repository.token;

import com.ichop.core.domain.entities.tokens.PasswordResetToken;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends TokenRepository<PasswordResetToken> {

}
