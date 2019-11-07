package ichop.tokens.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.tokens.domain.entities.Token;
import ichop.tokens.domain.models.service.TokenServiceModel;
import ichop.tokens.repositories.TokenRepository;
import org.ichop.commons.service.AbstractBaseService;


public abstract class AbstractTokenServices
        <E extends Token, S extends TokenServiceModel, R extends TokenRepository<E>>
        extends AbstractBaseService<E, S, R>
        implements TokenServices<S> {

    private Class<S> serviceModelClass;

    public AbstractTokenServices(ObjectMapper objectMapper, R repository) {
        super(objectMapper, repository);

    }

    @Override
    public S findByUserUsername(String userId) {
        E token = super.repository.findByUserUsername(userId);
        return super.toServiceModel(token);
    }

    @Override
    public S findByToken(String tokeen) {
        E token = super.repository.findByToken(tokeen);
        return super.toServiceModel(token);
    }

    @Override
    public boolean existsByToken(String tokeen) {
        return super.repository.existsByToken(tokeen);
    }

    @Override
    public Long deleteByToken(String tokeen) {
        return super.repository.deleteByToken(tokeen);
    }
}
