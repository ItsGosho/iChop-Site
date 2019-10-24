package ichop.core.areas.token.domain.models.service;

import ichop.core.base.BaseServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PasswordResetTokenServiceModel extends BaseServiceModel {

    private String token;
    private UserServiceModel user;
    private LocalDateTime expiryDate;
    private Integer TOKEN_EXPIRATION_IN_SECONDS;
}
