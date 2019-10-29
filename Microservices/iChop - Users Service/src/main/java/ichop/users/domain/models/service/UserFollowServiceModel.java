package ichop.users.domain.models.service;

import ichop.users.common.domain.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserFollowServiceModel extends BaseServiceModel {

    private UserServiceModel user;
    private UserServiceModel follower;
    private LocalDateTime since;

}
