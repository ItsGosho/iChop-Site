package ichop.tokens.domain.entities;

import ichop.tokens.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Token extends BaseEntity {

    @NotNull
    private String userId;

    @NotNull
    private String token;

    @NotNull
    private LocalDateTime expirationDate;
}
