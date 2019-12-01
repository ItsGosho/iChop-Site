package ichop.core.areas.thread.models.jms.create;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class ThreadCreateRequest extends RequestCandidate {


    private String title;
    private String content;
    private String creatorUsername;

}
