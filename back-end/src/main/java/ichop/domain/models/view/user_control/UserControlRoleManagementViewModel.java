package ichop.domain.models.view.user_control;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserControlRoleManagementViewModel extends UserControlBaseViewModel {

    private String nextRole;
    private String previousRole;

}
