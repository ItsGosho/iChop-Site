package ichop.core.areas.thread.models.jms.retrieve;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThreadsFindAllRequest extends RequestCandidate {
    
    private Pageable pageable;
    
}