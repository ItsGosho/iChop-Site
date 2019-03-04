package ichop.domain.models.service.token;

import ichop.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PasswordResetTokenServiceModel {

    private String id;
    private String token;
    private UserServiceModel user;
    private LocalDateTime expiryDate;
    private Integer TOKEN_EXPIRATION_IN_SECONDS;
}
