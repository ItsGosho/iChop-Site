package ichop.users.domain.models.jms.retrieve;

import ichop.users.common.domain.BaseReplyModel;
import ichop.users.common.domain.BaseRequestModel;
import ichop.users.domain.models.service.UserServiceModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersAllReply extends BaseReplyModel {

    private List<UserServiceModel> users;

}
