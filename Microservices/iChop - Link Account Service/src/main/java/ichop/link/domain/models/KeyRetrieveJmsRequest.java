package ichop.link.domain.models;

import com.ichop.plugin.linkaccount.commons.domain.RequestCandidate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyRetrieveJmsRequest extends RequestCandidate {

    private String linkKey;

}
