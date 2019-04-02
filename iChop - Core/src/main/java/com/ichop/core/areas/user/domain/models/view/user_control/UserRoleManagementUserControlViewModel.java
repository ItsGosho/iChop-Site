package com.ichop.core.areas.user.domain.models.view.user_control;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRoleManagementUserControlViewModel extends UserControlBaseViewModel {

    private String nextRole;
    private String previousRole;
    private List<UserRoleManagementHistoryUserControlViewModel> roleChangeHistory;

}
