package ichop.users.domain.models.service;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseServiceModel;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserFollowServiceModel extends BaseServiceModel {

    private UserServiceModel user;
    private UserServiceModel follower;
    private LocalDateTime since;

}
