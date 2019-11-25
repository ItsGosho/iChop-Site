package ichop.core.areas.storage.models.jms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSetAvatarRequest extends RequestCandidate {

    private String username;
    private String avatar;

}
