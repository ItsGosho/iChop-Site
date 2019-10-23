package ichop.tokens.domain.models.jms.create;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class TokenCreateReply {

    private String id;
    private String userId;
    private String token;
    private LocalDateTime creationDate;

}
