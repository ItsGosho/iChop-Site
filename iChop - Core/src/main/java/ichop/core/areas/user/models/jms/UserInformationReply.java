package ichop.core.areas.user.models.jms;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.ReplyCandidate;

import java.time.LocalDate;

@Getter
@Setter
public class UserInformationReply extends ReplyCandidate {

    private String id;
    private String statusMessage;
    private LocalDate birthDate;
    private String aboutYou;
    private UserReply user;

}
