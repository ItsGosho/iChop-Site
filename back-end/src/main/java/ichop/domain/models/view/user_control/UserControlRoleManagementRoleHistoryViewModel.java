package ichop.domain.models.view.user_control;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserControlRoleManagementRoleHistoryViewModel {

    private LocalDateTime happenedOn;
    private String message;

}
