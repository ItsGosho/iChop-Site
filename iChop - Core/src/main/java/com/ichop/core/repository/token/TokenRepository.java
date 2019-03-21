package com.ichop.core.repository.token;

import com.ichop.core.domain.entities.tokens.BaseToken;
import com.ichop.core.domain.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TokenRepository<T extends BaseToken> extends JpaRepository<T,String> {

    T findByUser(User user);
    T findByToken(String token);
}
