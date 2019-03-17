package ichop.domain.models.view.user_control;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserControlRoleManagementViewModel extends UserControlBaseViewModel {

    private String nextRole;
    private String previousRole;
    private List<UserControlRoleManagementRoleHistoryViewModel> roleChangeHistory;

}
