package ichop.core.areas.thread.models.jms.increase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThreadIncreaseViewsRequest extends RequestCandidate {

    private String id;

}
