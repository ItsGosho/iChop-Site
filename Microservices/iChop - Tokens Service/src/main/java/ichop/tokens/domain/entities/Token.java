package ichop.tokens.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Token extends BaseEntity {

    @NotNull
    private String userUsername;

    @NotNull
    private String token;

    @NotNull
    private LocalDateTime creationDate = LocalDateTime.now();
}
