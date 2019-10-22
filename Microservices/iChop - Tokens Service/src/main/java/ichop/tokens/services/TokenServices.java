package ichop.tokens.services;

import ichop.tokens.common.service.BaseService;
import ichop.tokens.domain.models.service.TokenServiceModel;

public interface TokenServices<S extends TokenServiceModel> extends BaseService<S> {
    S findByUserId(String userId);

    S findByToken(String tokeen);

    boolean existsByToken(String tokeen);
}