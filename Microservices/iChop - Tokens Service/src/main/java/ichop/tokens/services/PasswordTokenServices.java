package ichop.tokens.services;

import ichop.tokens.domain.models.service.PasswordTokenServiceModel;

public interface PasswordTokenServices extends TokenServices<PasswordTokenServiceModel> {

    boolean isValid(String token);

}
