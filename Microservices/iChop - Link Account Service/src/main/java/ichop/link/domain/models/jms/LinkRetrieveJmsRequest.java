package ichop.link.domain.models.jms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkRetrieveJmsRequest extends RequestCandidate {

    private String candidateUID;

}
