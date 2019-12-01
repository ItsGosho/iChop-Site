package ichop.link.domain.models;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LinkCreateJmsRequest {

    private String candidateUID;
    private String linkKey;
}
