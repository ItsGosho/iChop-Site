package com.ichop.core.areas.token.repositories;

import com.ichop.core.areas.token.domain.entities.PasswordResetToken;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends TokenRepository<PasswordResetToken> {

}
