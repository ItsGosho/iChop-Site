package ichop.users.domain.models.jms.role;

import ichop.users.common.domain.BaseReplyModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserHasPreviousRoleReply extends BaseReplyModel {

    private boolean hasPreviousRole;

}
