package ichop.core.areas.user.models.jms.retrieve;


import ichop.core.common.domain.BaseRequestModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFindByEmailRequest extends BaseRequestModel {

    private String email;

}
