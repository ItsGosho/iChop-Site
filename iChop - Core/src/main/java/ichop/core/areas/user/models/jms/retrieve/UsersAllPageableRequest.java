package ichop.core.areas.user.models.jms.retrieve;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class UsersAllPageableRequest extends BaseRequestModel {

    private Pageable pageable;

}
