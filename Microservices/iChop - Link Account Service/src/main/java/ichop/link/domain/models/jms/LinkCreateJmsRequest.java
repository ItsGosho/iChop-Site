package ichop.link.domain.models.jms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkCreateJmsRequest extends RequestCandidate {

    private String linkKey;
    private String candidateUID;
}
