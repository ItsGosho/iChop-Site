package ichop.threads.domain.models.jms.retrieve;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.domain.RestPageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@Getter
@Setter
public class ThreadsFindAllRequest extends RequestCandidate {
    
    private Map<String,Object> pageable;
    
}