package ichop.core.areas.user.models.jms.retrieve;

import ichop.core.common.domain.BaseReplyModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersAllPageableReply extends BaseReplyModel {

    private List<UserFindByEmailReply> users;

}
