package ichop.tokens.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.tokens.common.service.AbstractBaseService;
import ichop.tokens.domain.entities.Token;
import ichop.tokens.domain.models.service.TokenServiceModel;
import ichop.tokens.repositories.TokenRepository;

public abstract class AbstractTokenServices
        <E extends Token, S extends TokenServiceModel, R extends TokenRepository<E>>
        extends AbstractBaseService<E, S, R>
        implements TokenServices<S> {

    public AbstractTokenServices(ObjectMapper objectMapper, R repository) {
        super(objectMapper, repository);
    }
}
