package ichop.tokens.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.tokens.common.service.AbstractBaseService;
import ichop.tokens.domain.entities.PasswordToken;
import ichop.tokens.domain.models.service.PasswordTokenServiceModel;
import ichop.tokens.domain.models.service.TokenServiceModel;
import ichop.tokens.repositories.PasswordTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PasswordTokenServicesImp
        extends AbstractTokenServices<PasswordToken, PasswordTokenServiceModel, PasswordTokenRepository>
        implements PasswordTokenServices {

    @Value("${token.password.expiration_in_hours}")
    private Integer tokenExpiration;

    @Autowired
    public PasswordTokenServicesImp(ObjectMapper objectMapper, PasswordTokenRepository repository) {
        super(objectMapper, repository);
    }


    @Override
    public boolean isValid(String tokeen) {
        TokenServiceModel token = super.findByToken(tokeen);

        return this.isExpired(token.getCreationDate());
    }

    private boolean isExpired(LocalDateTime dateTime) {
        return LocalDateTime.now().isBefore(dateTime.plusHours(this.tokenExpiration));
    }
}
