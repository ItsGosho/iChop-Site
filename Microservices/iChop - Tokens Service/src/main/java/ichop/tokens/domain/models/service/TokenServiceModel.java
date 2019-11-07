package ichop.tokens.domain.models.service;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseServiceModel;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class TokenServiceModel extends BaseServiceModel {

    private String userUsername;
    private String token;
    private LocalDateTime creationDate = LocalDateTime.now();

}
