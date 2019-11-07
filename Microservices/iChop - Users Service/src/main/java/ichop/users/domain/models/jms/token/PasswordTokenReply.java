package ichop.users.domain.models.jms.token;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.ReplyCandidate;

import java.time.LocalDateTime;

@Getter
@Setter
public class PasswordTokenReply extends ReplyCandidate {

    private String id;
    private String userUsername;
    private String token;
    private LocalDateTime creationDate;

}
