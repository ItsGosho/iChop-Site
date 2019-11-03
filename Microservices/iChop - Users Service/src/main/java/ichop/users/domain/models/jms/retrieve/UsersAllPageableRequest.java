package ichop.users.domain.models.jms.retrieve;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsersAllPageableRequest extends RequestCandidate {

    @NotNull
    private Pageable pageable;

}
