package ichop.tokens.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.tokens.common.service.AbstractBaseService;
import ichop.tokens.domain.entities.PasswordToken;
import ichop.tokens.domain.models.service.PasswordTokenServiceModel;
import ichop.tokens.domain.models.service.TokenServiceModel;
import ichop.tokens.repositories.PasswordTokenRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
    public Long deleteAllByUser(String userId) {
        return super.repository.deleteAllByUserId(userId);
    }

    @Override
    public boolean isValid(String tokeen) {
        TokenServiceModel token = super.findByToken(tokeen);

        return this.isExpired(token.getCreationDate());
    }

    @Override
    public List<PasswordTokenServiceModel> findExpired() {
        return super.repository
                .findAll()
                .stream()
                .filter(x -> this.isExpired(x.getCreationDate()))
                .map(x -> super.objectMapper.convertValue(x, PasswordTokenServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public String generateToken() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    private boolean isExpired(LocalDateTime dateTime) {
        return LocalDateTime.now().isBefore(dateTime.plusHours(this.tokenExpiration));
    }
}
