package ichop.tokens.domain.models.service;

import ichop.tokens.common.domain.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class TokenServiceModel extends BaseServiceModel {

    private String userId;
    private String token;
    private LocalDateTime creationDate = LocalDateTime.now();

}
