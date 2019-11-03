package ichop.tokens.services;

import ichop.tokens.domain.models.service.TokenServiceModel;
import org.ichop.commons.service.BaseService;

public interface TokenServices<S extends TokenServiceModel> extends BaseService<S> {

    S findByUserUsername(String username);

    S findByToken(String tokeen);

    boolean existsByToken(String tokeen);

    Long deleteByToken(String tokeen);
}
