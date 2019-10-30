package ichop.users.domain.models.jms.retrieve;

import ichop.users.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsersAllPageableRequest extends BaseRequestModel {

    @NotNull
    private Pageable pageable;

}
