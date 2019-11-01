package ichop.core.areas.user.models.jms.retrieve;

import ichop.core.common.domain.BaseRequestModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFindByUsernameRequest extends BaseRequestModel {

    @NotNull
    private String username;

}
