package ichop.tokens.services;

import ichop.tokens.domain.models.service.PasswordTokenServiceModel;

import java.util.List;

public interface PasswordTokenServices extends TokenServices<PasswordTokenServiceModel> {

    Long deleteAllByUserUsername(String userId);
    boolean isValid(String token);
    List<PasswordTokenServiceModel> findExpired();

    String generateToken();
}
